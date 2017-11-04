package com.lmml.graph.common.activiti.beans;

import java.util.HashMap;
import java.util.Map;

public class BpmTaskCommand {
    private Map<String, Object> variableMap = new HashMap<String, Object>();

    public Map<String, Object> getVariableMap() {
        return variableMap;
    }

    public void setVariableMap(Map<String, Object> variableMap) {
        this.variableMap = variableMap;
    }
}