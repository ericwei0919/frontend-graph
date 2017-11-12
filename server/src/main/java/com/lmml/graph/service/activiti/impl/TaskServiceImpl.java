package com.lmml.graph.service.activiti.impl;

import com.lmml.graph.common.activiti.TransitProcessInstanceService;
import com.lmml.graph.common.activiti.beans.BpmTaskCommand;
import com.lmml.graph.common.activiti.beans.PagingTask;
import com.lmml.graph.common.activiti.beans.ProcessInstance;
import com.lmml.graph.common.interceptor.AuthService;
import com.lmml.graph.service.activiti.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private AuthService authService;

    @Autowired
    private TransitProcessInstanceService transitProcessInstanceService;

    @Override
    public PagingTask<ProcessInstance> getToDoTasks(int index, int pageSize) {
        return transitProcessInstanceService.getToDoTasks(authService.getUserId(),0,50);
    }

    @Override
    public PagingTask<ProcessInstance> getInProgressTasks(int index, int pageSize) {
        return transitProcessInstanceService.getInProgressTasks(authService.getUserId(),0,50);
    }

    @Override
    public PagingTask<ProcessInstance> getCompletedTasks(int index, int pageSize) {
        return transitProcessInstanceService.getCompletedTasks(authService.getUserId(),0,50);
    }

    @Override
    public PagingTask<ProcessInstance> getClaimTasks(int index, int pageSize) {
        return transitProcessInstanceService.getClaimTasks(authService.getUserId(),0,50);
    }

    @Override
    public boolean assignTask(Long hostObjectId, BpmTaskCommand bpmTaskCommand) {
        return transitProcessInstanceService.assignTask(authService.getUserId(),hostObjectId,bpmTaskCommand);
    }

    @Override
    public boolean completeTask(Long hostObjectId, BpmTaskCommand bpmTaskCommand) {
        return transitProcessInstanceService.completeTask(authService.getUserId(),hostObjectId,bpmTaskCommand,bpmTaskCommand.getVariableMap());
    }

    @Override
    public boolean assignAndCompleteTask(Long hostObjectId, BpmTaskCommand bpmTaskCommand) {
        return transitProcessInstanceService.assignAndCompleteTask(authService.getUserId(),hostObjectId,bpmTaskCommand);

    }

}
