package com.lmml.graph.common.activiti;

import com.lmml.graph.common.activiti.beans.TaskDetails;
import org.activiti.engine.task.TaskInfo;

import java.util.List;
import java.util.Map;

public interface InquireWorkFlowService {

    TaskDetails getProcessInstance(String hostObjId);

    List<TaskInfo> getToDoTasks(String userId);

    List<TaskInfo> getInProgressTasks(String userId);

    List<TaskInfo> getCompletedTasks(String userId);

    TaskInfo getUpcomingTask(String userId, String hostObjId);

    List<TaskInfo> getTaskByAssignee(String assignee, String processDefKey, Map<String, Object> varFilters);

    List<TaskInfo> getTaskByCandidateGroup(String candidateGroup, String processDefKey, Map<String, Object> variableFilters);

}
