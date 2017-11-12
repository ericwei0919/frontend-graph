package com.lmml.graph.common.activiti.beans;

import java.util.Map;

public interface BpmTaskSupport {
    ProcessInstance generateProcessInstance(String sourceObjId, String userId, Map<String, Object> processVars);
}