package com.lmml.graph.common.activiti;

import com.lmml.graph.common.activiti.beans.PagingTask;
import com.lmml.graph.common.activiti.beans.ProcessInstance;

public interface TransitProcessInstanceService {

    ProcessInstance getProcessInstance(String hostObjId);

    PagingTask<ProcessInstance> getToDoTasks(String userId, int index, int pageSize);

    PagingTask<ProcessInstance> getInProgressTasks(String userId, int index, int pageSize);

    PagingTask<ProcessInstance> getCompletedTasks(String userId, int index, int pageSize);

}
