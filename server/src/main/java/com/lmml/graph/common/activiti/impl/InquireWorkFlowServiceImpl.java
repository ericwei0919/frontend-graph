package com.lmml.graph.common.activiti.impl;

import com.lmml.graph.common.activiti.InquireWorkFlowService;
import com.lmml.graph.common.activiti.beans.TaskDetails;
import com.lmml.graph.common.util.WorkflowConst;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.*;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskInfo;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<TaskInfo> getProcessInstance(Long actBusinessId) {
        if (null == actBusinessId) {
            return null;
        }
        TaskQuery query = taskService.createTaskQuery()
                .processVariableValueEquals(WorkflowConst.KEY_ACT_THREA_CODE, actBusinessId);
        return this.getTaskInfos(query, null, null);
    }

    @Override
    public List<TaskInfo> getProcessInstance(Long assignee, Long actBusinessId) {
        TaskQuery query = taskService.createTaskQuery().taskAssignee(String.valueOf(assignee))
                .processVariableValueEquals(WorkflowConst.KEY_ACT_THREA_CODE, actBusinessId);
        return this.getTaskInfos(query, null, null);
    }

    @Override
    public List<TaskInfo> getTaskByAssignee(Long assignee, String processDefKey, Map<String, Object> varFilters) {
        if (null == assignee) {
            return null;
        }
        this.getTaskTypes(varFilters);
        TaskQuery query = taskService.createTaskQuery().taskAssignee(String.valueOf(assignee))
                .includeProcessVariables().orderByTaskCreateTime().asc();
        return this.getTaskInfos(query, processDefKey, varFilters);
    }

    @Override
    public List<TaskInfo> getTaskByCandidateGroup(List<Long> candidateGroups, String processDefKey, Map<String, Object> variableFilters) {
        if (null == candidateGroups) {
            return null;
        }
        List<String> queryGroups = new ArrayList<>();
        for (Long candidateGroupId : candidateGroups) {
            queryGroups.add(String.valueOf(candidateGroupId));
        }
        if (queryGroups.size()>0){
            TaskQuery query = taskService.createTaskQuery().taskCandidateGroupIn(queryGroups).includeProcessVariables();
            return this.getTaskInfos(query, processDefKey, variableFilters);
        }
        return null;
    }


    @Override
    public List<TaskInfo> getTaskByCandidateUser(Long candidateUser, String processDefKey, Map<String, Object> variableFilters) {
        TaskQuery query = taskService.createTaskQuery()
                .taskCandidateUser(String.valueOf(candidateUser)).includeProcessVariables();
        return this.getTaskInfos(query, processDefKey, variableFilters);
    }


    @Override
    public List<TaskInfo> getCompletedTasks(Long userId, String processDefKey, Boolean isProcessFinished) {
        HistoricTaskInstanceQuery query = historyService.createHistoricTaskInstanceQuery().finished();
        if (null != userId) {
            query.taskAssignee(String.valueOf(userId));
        }
        query.includeProcessVariables();
        if (isProcessFinished != null) {
            if (isProcessFinished) {
                query.processFinished();
            } else {
                query.processUnfinished();
            }
        }
        if (processDefKey != null) {
            query.processDefinitionKey(processDefKey);
        }
        return (List<TaskInfo>) (List<?>) query.list();
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

    List<TaskInfo> getTaskTypes(Map<String, Object> varFilters) {
        List<HistoricProcessInstance> list = historyService
                .createHistoricProcessInstanceQuery()
                .processDefinitionKey(WorkflowConst.KEY_ACT_TYPE_CODE)
                .orderByProcessInstanceStartTime().asc()//排序
                .list();
        if (list != null && list.size() > 0) {
            for (HistoricProcessInstance hpi : list) {
                System.out.println("流程定义ID：" + hpi.getProcessDefinitionId());
                System.out.println("流程实例ID：" + hpi.getId());
                System.out.println("开始时间：" + hpi.getStartTime());
                System.out.println("结束时间：" + hpi.getEndTime());
                System.out.println("流程持续时间：" + hpi.getDurationInMillis());
                System.out.println("=======================================");
            }
        }
        return  null;
    }

    public void queryHistoricActivitiInstance() {
        String processInstanceId = "27501";
        List<HistoricActivityInstance> list = historyService
                .createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId)
                .list();
        if (list != null && list.size() > 0) {
            for (HistoricActivityInstance hai : list) {
                System.out.println(hai.getId());
                System.out.println("步骤ID：" + hai.getActivityId());
                System.out.println("步骤名称：" + hai.getActivityName());
                System.out.println("执行人：" + hai.getAssignee());
                System.out.println("====================================");
            }
        }
    }

    public void queryHistoricVariables() {
        String processInstanceId = "37501";
        List<HistoricVariableInstance> list = historyService
                .createHistoricVariableInstanceQuery()
                .processInstanceId(processInstanceId)
                .list();
        if(list != null && list.size()>0){
            for(HistoricVariableInstance hvi : list){
                System.out.print("piId:"+hvi.getProcessInstanceId()+"，");
                System.out.print("variablesName:"+hvi.getVariableName()+"，");
                System.out.println("variablesValue:"+hvi.getValue()+";");
            }
        }
    }

}
