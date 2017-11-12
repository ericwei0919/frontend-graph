package com.lmml.graph.controller.activiti;

import com.lmml.graph.common.util.ResponseWrapper;
import com.lmml.graph.dto.activiti.TaskTemplateDto;
import com.lmml.graph.service.activiti.TaskTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/template")
public class TaskTemplateController {

    @Autowired
    private TaskTemplateService taskTemplateService;

    @PostMapping
    public ResponseWrapper startTask(@RequestBody TaskTemplateDto taskTemplateDto) throws Exception {
        boolean success = taskTemplateService.startTask(taskTemplateDto);
        return ResponseWrapper.success(success);
    }
}
