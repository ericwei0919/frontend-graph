package com.lmml.graph.common.activiti;

import com.lmml.graph.common.activiti.beans.BpmTaskCommand;

import java.util.List;
import java.util.Map;

public interface ProcessWorkFlowService {

    String start(String processDefinitionKey, Map<String, Object> variableMap);

    boolean assignTask(String userId, String hostObjId, BpmTaskCommand command);

    boolean completeTask(String userId, String hostObjId, BpmTaskCommand command);

    boolean assignAndCompleteTask(String userId, String hostObjId, BpmTaskCommand command);

    boolean processTaskThenDesignatedHandler(String userId, String hostObjId, List<Long> groupIds, BpmTaskCommand command);

    boolean processTaskThenDesignatedProcessingDepartment(String userId, String hostObjId, List<Long> groupIds, BpmTaskCommand command);

    boolean terminateTheProcess(String hostObjId);

    boolean endTheProcess(String hostObjId);

    boolean deleteTheProcess(String hostObjId);

}