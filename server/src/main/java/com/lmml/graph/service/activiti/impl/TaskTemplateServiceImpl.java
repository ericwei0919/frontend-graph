package com.lmml.graph.service.activiti.impl;

import com.lmml.graph.common.activiti.ProcessWorkFlowService;
import com.lmml.graph.common.interceptor.AuthService;
import com.lmml.graph.common.util.IdentifierUtil;
import com.lmml.graph.common.util.WorkflowConst;
import com.lmml.graph.domain.activiti.ActivitiSummary;
import com.lmml.graph.domain.activiti.ActivitiTaskTemplate;
import com.lmml.graph.domain.authority.RbacUser;
import com.lmml.graph.dto.activiti.TaskTemplateDto;
import com.lmml.graph.repository.activiti.TaskTemplateRepository;
import com.lmml.graph.service.activiti.ActivitiSummaryService;
import com.lmml.graph.service.activiti.TaskTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

import java.util.HashMap;
import java.util.Map;

@Service
public class TaskTemplateServiceImpl implements TaskTemplateService {

    @Autowired
    private AuthService authService;
    @Autowired
    private TaskTemplateRepository taskTemplateRepo;
    @Autowired
    private ActivitiSummaryService activitiSummaryService;
    @Autowired
    private ProcessWorkFlowService processWorkFlowService;


    @Override
    public boolean startTask(TaskTemplateDto taskTemplateDto) {
        Long actBusinessId = IdentifierUtil.generateID();
        ActivitiTaskTemplate activitiTaskTemplate = new ActivitiTaskTemplate();
        activitiTaskTemplate.setTaskTemplateId(actBusinessId);
        activitiTaskTemplate.setContent(taskTemplateDto.getContent());
        activitiTaskTemplate.setTemplateName(taskTemplateDto.getTemplateName());
        activitiTaskTemplate.setCreateTimestamp(new Timestamp(new Date().getTime()));
        taskTemplateRepo.save(activitiTaskTemplate);
        long activitiSummaryId = IdentifierUtil.generateID();
        Map<String, Object> variableMap = taskTemplateDto.getBpmTaskCommand().getVariableMap();
        if (variableMap == null ){
            variableMap = new HashMap<>();
        }
        variableMap.put(WorkflowConst.KEY_ACT_THREA_CODE, activitiSummaryId);
        variableMap.put("applyer", String.valueOf(authService.getUserId()));
        String processInstanceId = processWorkFlowService.start("activiti_designated_approval", variableMap);
        ActivitiSummary activitiSummary = new ActivitiSummary();
        RbacUser assignee = new RbacUser();
        assignee.setUserId(authService.getUserId());
        activitiSummary.setAssignee(assignee);
        activitiSummary.setCreateTimestamp(new Timestamp(new Date().getTime()));
        activitiSummary.setActivitiType(taskTemplateDto.getTemplateName());
        activitiSummary.setHostObjId(actBusinessId);
        activitiSummary.setProcessInstanceId(processInstanceId);
        activitiSummary.setTaskStatus("待认领");
        activitiSummary.setTableName("activiti_task_template");
        activitiSummary.setId(activitiSummaryId);
        activitiSummaryService.save(activitiSummary);
        return true;
    }


}
