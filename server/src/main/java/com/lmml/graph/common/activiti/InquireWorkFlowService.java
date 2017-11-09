package com.lmml.graph.common.activiti;

import org.activiti.engine.task.TaskInfo;

import java.util.List;
import java.util.Map;

public interface InquireWorkFlowService {

    List<TaskInfo> getProcessInstance(Long actBusinessId);

    List<TaskInfo> getProcessInstance(Long assignee,Long actBusinessId);

    List<TaskInfo> getTaskByAssignee(Long assignee, String processDefKey, Map<String, Object> varFilters);

    List<TaskInfo> getTaskByCandidateGroup(List<Long> candidateGroups, String processDefKey, Map<String, Object> variableFilters);

    List<TaskInfo> getTaskByCandidateUser(Long candidateUser, String processDefKey, Map<String, Object> variableFilters);

    List<TaskInfo> getCompletedTasks(Long userId, String processDefKey, Boolean isProcessFinished);
}
