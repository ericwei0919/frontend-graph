package com.lmml.graph.service.activiti;

import com.lmml.graph.common.activiti.beans.BpmTaskCommand;
import com.lmml.graph.common.activiti.beans.PagingTask;
import com.lmml.graph.common.activiti.beans.ProcessInstance;

public interface TaskService {

    PagingTask<ProcessInstance> getToDoTasks(int index, int pageSize);

    PagingTask<ProcessInstance> getInProgressTasks(int index, int pageSize);

    PagingTask<ProcessInstance> getCompletedTasks(int index, int pageSize);

    PagingTask<ProcessInstance> getClaimTasks(int index, int pageSize);

    boolean assignTask(Long hostObjectId, BpmTaskCommand bpmTaskCommand);

    boolean completeTask(Long hostObjectId, BpmTaskCommand bpmTaskCommand);

    boolean assignAndCompleteTask(Long hostObjectId, BpmTaskCommand bpmTaskCommand);
}
