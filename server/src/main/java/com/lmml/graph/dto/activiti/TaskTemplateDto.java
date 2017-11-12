package com.lmml.graph.dto.activiti;

import com.lmml.graph.common.activiti.beans.BpmTaskCommand;
import lombok.Data;

@Data
public class TaskTemplateDto {
    private Long taskTemplateId;
    private String templateName;
    private String createTimeLabel;
    private String content;
    private BpmTaskCommand bpmTaskCommand;
}
