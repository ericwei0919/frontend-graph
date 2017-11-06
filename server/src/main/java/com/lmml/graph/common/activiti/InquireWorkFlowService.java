package com.lmml.graph.common.activiti;

import com.lmml.graph.common.activiti.beans.PagingTask;
import com.lmml.graph.common.activiti.beans.TaskDetails;

public interface InquireWorkFlowService {

    TaskDetails getProcessInstance(String hostObjId);

    PagingTask<TaskDetails> getToDoTasks(String userId, int index, int pageSize);

    PagingTask<TaskDetails> getInProgressTasks(String userId, int index, int pageSize);

    PagingTask<TaskDetails> getCompletedTasks(String userId, int index, int pageSize);

}
