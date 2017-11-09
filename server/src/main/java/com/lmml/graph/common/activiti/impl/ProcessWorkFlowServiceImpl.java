package com.lmml.graph.common.activiti.impl;

import com.lmml.graph.common.activiti.InquireWorkFlowService;
import com.lmml.graph.common.activiti.ProcessWorkFlowService;
import com.lmml.graph.common.activiti.beans.BpmTaskCommand;
import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProcessWorkFlowServiceImpl implements ProcessWorkFlowService {

    public static final String KEY_TASK_OBJ_UID = "taskObjectUid";
    public static final String KEY_PROCESS_TYPE = "processType";

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;

    @Autowired
    private InquireWorkFlowService inquireWorkFlowService;

    final static Logger logger = Logger.getLogger(ProcessWorkFlowServiceImpl.class);

    @Override
    public String start(String processDefinitionKey, Map<String, Object> variableMap) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, variableMap);
        return processInstance.getId();
    }

    @Override
    public boolean completeTask(Long userId, Long hostObjId, BpmTaskCommand command, Map<String, Object> variableMap) {
        try {
            List<TaskInfo> taskInfo = inquireWorkFlowService.getProcessInstance(userId, hostObjId);
            taskService.complete(taskInfo.get(0).getId(), variableMap);
        } catch (Exception ex) {
            logger.error("任务[" + userId + "]没有完成成功, 原因是:" + ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean assignAndCompleteTask(Long userId, Long hostObjId, BpmTaskCommand command) {
        List<TaskInfo> assignTasks = inquireWorkFlowService.getProcessInstance(hostObjId);
        try {
            taskService.claim(assignTasks.get(0).getId(), String.valueOf(userId));
        } catch (Exception ex) {
            logger.error("任务[" + userId + "]没有分配成功, 原因是:" + ex.getMessage());
            return false;
        }
        List<TaskInfo> completeTasks = inquireWorkFlowService.getProcessInstance(userId, hostObjId);
        try {
            if (completeTasks.get(0).getProcessInstanceId().equals(assignTasks.get(0).getProcessInstanceId())) {
                taskService.complete(completeTasks.get(0).getId(), command.getVariableMap());
            } else {
                throw new Exception("任务不匹配");
            }
        } catch (Exception ex) {
            logger.error("任务[" + userId + "]没有完成成功, 原因是:" + ex.getMessage());
            return false;
        }
        return false;
    }

    @Override
    public boolean processTaskThenDesignatedHandler(Long userId, Long hostObjId, List<Long> users, BpmTaskCommand command) {
        return false;
    }

    @Override
    public boolean processTaskThenDesignatedProcessingDepartment(Long userId, Long hostObjId, List<Long> groupIds, BpmTaskCommand command) {
        return false;
    }

    @Override
    public boolean terminateTheProcess(Long hostObjId) {
        return false;
    }

    @Override
    public boolean endTheProcess(Long hostObjId) {
        return false;
    }

    @Override
    public boolean deleteTheProcess(Long hostObjId) {
        return false;
    }

    @Override
    public boolean assignTask(Long userId, Long hostObjId, BpmTaskCommand command) {
        try {
            List<TaskInfo> taskInfo = inquireWorkFlowService.getProcessInstance(hostObjId);
            taskService.claim(taskInfo.get(0).getId(), String.valueOf(userId));
        } catch (Exception ex) {
            logger.error("任务[" + userId + "]没有分配成功, 原因是:" + ex.getMessage());
            return false;
        }
        return true;
    }


}
