package com.lmml.graph.common.activiti.impl;

import com.lmml.graph.common.activiti.InquireWorkFlowService;
import com.lmml.graph.common.activiti.beans.PagingTask;
import com.lmml.graph.common.activiti.beans.TaskDetails;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskInfo;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class InquireWorkFlowServiceImpl implements InquireWorkFlowService {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;

    @Override
    public TaskDetails getProcessInstance(String hostObjId) {
        return null;
    }

    @Override
    public List<TaskInfo> getToDoTasks(String userId) {
        return null;
    }

    @Override
    public List<TaskInfo> getInProgressTasks(String userId) {
        return null;
    }

    @Override
    public List<TaskInfo> getCompletedTasks(String userId) {
        return null;
    }

    @Override
    public TaskInfo getUpcomingTask(String userId, String hostObjId) {
        return null;
    }


    @Override
    public List<TaskInfo> getTaskByAssignee(String assignee, String processDefKey, Map<String, Object> varFilters) {
        TaskQuery query = taskService.createTaskQuery().taskAssignee(assignee)
                .includeProcessVariables().orderByTaskCreateTime().asc();
        return this.getTaskInfos(query, processDefKey, varFilters);
    }

    @Override
    public List<TaskInfo> getTaskByCandidateGroup(String candidateGroup, String processDefKey, Map<String, Object> varFilters) {

        TaskQuery query = taskService.createTaskQuery().taskCandidateGroup(candidateGroup).includeProcessVariables();
        return this.getTaskInfos(query, processDefKey, varFilters);
    }



    List<TaskInfo> getTaskInfos(TaskQuery query, String processDefKey, Map<String, Object> varFilters) {
        if (processDefKey != null) {
            query.processDefinitionKey(processDefKey);
        }
        if (varFilters != null) {
            Iterator<Map.Entry<String, Object>> itr = varFilters.entrySet().iterator();
            while (itr.hasNext()) {
                Map.Entry<String, Object> entry = itr.next();
                query.processVariableValueEquals(entry.getKey(), entry.getValue());
            }
        }
        List<Task> tasks = query.list();
        return (List<TaskInfo>) (List<?>) tasks;
    }

}
