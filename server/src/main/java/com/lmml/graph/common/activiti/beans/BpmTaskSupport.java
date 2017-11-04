package com.lmml.graph.common.activiti.beans;

import java.util.Map;

public interface BpmTaskSupport {

    String KEY_TASK_OBJ_UID = "taskObjectUid";
    String KEY_PROCESS_TYPE = "processType";
    String KEY_CURR_TASK_NAME = "currTaskName";
    String KEY_CURR_TASK_ASSIGNEE = "currTaskAssignee";

    boolean assign(String userId, String hostObjId);
    boolean complete(String userId, String hostObjId, Map<String, Object> processVariables);
    TaskInfo<?> generateTaskInfo(String sourceObjId, String userId, Map<String, Object> processVars);
}