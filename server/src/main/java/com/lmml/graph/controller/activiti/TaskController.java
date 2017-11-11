package com.lmml.graph.controller.activiti;

import com.lmml.graph.common.activiti.beans.PagingTask;
import com.lmml.graph.common.activiti.beans.ProcessInstance;
import com.lmml.graph.common.util.ResponseWrapper;
import com.lmml.graph.service.activiti.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;


    @GetMapping("/todo")
    public ResponseWrapper<PagingTask<ProcessInstance>> getToDoTasks() throws Exception {
        PagingTask<ProcessInstance> info = taskService.getToDoTasks(0,0);
        return ResponseWrapper.success(info);
    }

    @GetMapping("/inProgress")
    public ResponseWrapper<PagingTask<ProcessInstance>> getInProgressTasks() throws Exception {
        PagingTask<ProcessInstance> info = taskService.getInProgressTasks(0,0);
        return ResponseWrapper.success(info);
    }
    @GetMapping("/completed")
    public ResponseWrapper<PagingTask<ProcessInstance>> getCompletedTasks() throws Exception {
        PagingTask<ProcessInstance> info = taskService.getCompletedTasks(0,0);
        return ResponseWrapper.success(info);
    }
    @GetMapping("/claim")
    public ResponseWrapper<PagingTask<ProcessInstance>> getClaimTasks() throws Exception {
        PagingTask<ProcessInstance> info = taskService.getClaimTasks(0,0);
        return ResponseWrapper.success(info);
    }

}
