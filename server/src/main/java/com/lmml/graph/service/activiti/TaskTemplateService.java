package com.lmml.graph.service.activiti;


import com.lmml.graph.dto.activiti.TaskTemplateDto;

public interface TaskTemplateService {

    boolean startTask(TaskTemplateDto taskTemplateDto);
}
