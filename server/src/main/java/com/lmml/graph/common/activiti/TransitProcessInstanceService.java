package com.lmml.graph.common.activiti;

import com.lmml.graph.common.activiti.beans.BpmTaskCommand;
import com.lmml.graph.common.activiti.beans.PagingTask;
import com.lmml.graph.common.activiti.beans.ProcessInstance;

import java.util.Map;

public interface TransitProcessInstanceService {

    ProcessInstance getProcessInstance(Long actBusinessId);

    PagingTask<ProcessInstance> getToDoTasks(Long userId, int index, int pageSize);

    PagingTask<ProcessInstance> getInProgressTasks(Long userId, int index, int pageSize);

    PagingTask<ProcessInstance> getCompletedTasks(Long userId, int index, int pageSize);

    PagingTask<ProcessInstance> getClaimTasks(Long userId, int index, int pageSize);

    boolean assignTask(Long userId, Long hostObjId, BpmTaskCommand command);

    boolean completeTask(Long userId, Long hostObjId, BpmTaskCommand command,Map<String, Object> variableMap);

    boolean assignAndCompleteTask(Long userId, Long hostObjId, BpmTaskCommand command);
}
