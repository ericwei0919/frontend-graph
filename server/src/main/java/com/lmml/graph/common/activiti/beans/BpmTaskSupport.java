package com.lmml.graph.common.activiti.beans;

import java.util.Map;

public interface BpmTaskSupport {

    String KEY_TASK_OBJ_UID = "taskObjectUid";
    String KEY_PROCESS_TYPE = "processType";
    String KEY_CURR_TASK_NAME = "currTaskName";
    String KEY_CURR_TASK_ASSIGNEE = "currTaskAssignee";

    boolean assignTaskSucess(String userId, String hostObjId);
    boolean completeTaskSucess(String userId, String hostObjId, Map<String, Object> processVariables);
    ProcessInstance generateProcessInstance(String sourceObjId, String userId, Map<String, Object> processVars);
}