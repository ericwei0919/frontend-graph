package com.lmml.graph.common.activiti.impl;

import com.lmml.graph.common.activiti.InquireWorkFlowService;
import com.lmml.graph.common.activiti.TransitProcessInstanceService;
import com.lmml.graph.common.activiti.beans.PagingTask;
import com.lmml.graph.common.activiti.beans.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransitProcessInstanceServiceImpl implements TransitProcessInstanceService {

    @Autowired
    private InquireWorkFlowService inquireWorkFlowService;

    @Override
    public ProcessInstance getProcessInstance(String hostObjId) {
        return null;
    }

    @Override
    public PagingTask<ProcessInstance> getToDoTasks(String userId, int index, int pageSize) {
        return null;
    }

    @Override
    public PagingTask<ProcessInstance> getInProgressTasks(String userId, int index, int pageSize) {
        return null;
    }

    @Override
    public PagingTask<ProcessInstance> getCompletedTasks(String userId, int index, int pageSize) {
        return null;
    }
}
