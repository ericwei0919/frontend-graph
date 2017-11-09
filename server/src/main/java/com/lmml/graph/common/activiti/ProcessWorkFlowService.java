package com.lmml.graph.common.activiti;

import com.lmml.graph.common.activiti.beans.BpmTaskCommand;

import java.util.List;
import java.util.Map;

public interface ProcessWorkFlowService {

    String start(String processDefinitionKey, Map<String, Object> variableMap);

    boolean assignTask(Long userId, Long hostObjId, BpmTaskCommand command);

    boolean completeTask(Long userId, Long hostObjId, BpmTaskCommand command,Map<String, Object> variableMap);

    boolean assignAndCompleteTask(Long userId, Long hostObjId, BpmTaskCommand command);

    boolean processTaskThenDesignatedHandler(Long userId, Long hostObjId, List<Long> users, BpmTaskCommand command);

    boolean processTaskThenDesignatedProcessingDepartment(Long userId, Long hostObjId, List<Long> groupIds, BpmTaskCommand command);

    boolean terminateTheProcess(Long hostObjId);

    boolean endTheProcess(Long hostObjId);

    boolean deleteTheProcess(Long hostObjId);

}