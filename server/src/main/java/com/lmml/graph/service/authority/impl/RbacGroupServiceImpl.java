package com.lmml.graph.service.authority.impl;

import com.lmml.graph.common.activiti.ProcessWorkFlowService;
import com.lmml.graph.common.activiti.beans.BpmTaskCommand;
import com.lmml.graph.common.interceptor.AuthService;
import com.lmml.graph.common.util.IdentifierUtil;
import com.lmml.graph.common.util.WorkflowConst;
import com.lmml.graph.domain.activiti.ActivitiSummary;
import com.lmml.graph.domain.authority.RbacGroup;
import com.lmml.graph.domain.authority.RbacUser;
import com.lmml.graph.repository.authority.RbacGroupRepository;
import com.lmml.graph.service.activiti.ActivitiSummaryService;
import com.lmml.graph.service.authority.RbacGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RbacGroupServiceImpl implements RbacGroupService {

    @Autowired
    private RbacGroupRepository rbacGroupRepo;

    @Autowired
    private AuthService authService;

    @Autowired
    private ActivitiSummaryService activitiSummaryService;

    @Autowired
    private ProcessWorkFlowService processWorkFlowService;

    @Override
    public List<RbacGroup> findGroup() {
        return (List<RbacGroup>) rbacGroupRepo.findAll();
    }

    @Override
    public List<RbacGroup> save(List<RbacGroup> groups) {
        groups.forEach(rbacGroup -> {
            rbacGroup.setGroupId(IdentifierUtil.generateID());
            rbacGroup.setCreateTimestamp(new Timestamp(new Date().getTime()));
        });
        this.startActivit(groups.get(0).getGroupId());
        return (List<RbacGroup>) rbacGroupRepo.save(groups);
    }

    @Override
    public List<RbacGroup> findGroupByActivitiId(String activitiId) {
        return rbacGroupRepo.findGroupByActivitiId(activitiId);
    }

    boolean startActivit(Long actBusinessId) {
        Iterable<RbacGroup> all = rbacGroupRepo.findAll();
        StringBuffer approvalGroups = new StringBuffer();
        all.forEach(rbacGroup -> {
            if (actBusinessId.longValue() != rbacGroup.getGroupId().longValue()) {
                approvalGroups.append(String.valueOf(rbacGroup.getGroupId()) + ",");
            }
        });
        if (approvalGroups.length() > 0) {
            approvalGroups.deleteCharAt(approvalGroups.length() - 1);
        }
        long activitiSummaryId = IdentifierUtil.generateID();
        Map<String, Object> variableMap = new HashMap<>();
        variableMap.put("approvalGroups", approvalGroups.toString());
        variableMap.put("classify", "group");
        variableMap.put(WorkflowConst.KEY_ACT_THREA_CODE, activitiSummaryId);
        variableMap.put("applyer", String.valueOf(authService.getUserId()));
        String processInstanceId = processWorkFlowService.start("activiti_designated_approval", variableMap);
        ActivitiSummary activitiSummary = new ActivitiSummary();
        RbacUser assignee = new RbacUser();
        assignee.setUserId(authService.getUserId());
        activitiSummary.setAssignee(assignee);
        activitiSummary.setCreateTimestamp(new Timestamp(new Date().getTime()));
        activitiSummary.setActivitiType("分组新增");
        activitiSummary.setHostObjId(actBusinessId);
        activitiSummary.setProcessInstanceId(processInstanceId);
        activitiSummary.setTaskStatus("提交申请");
        activitiSummary.setTableName("rbac_group");
        activitiSummary.setId(activitiSummaryId);
        activitiSummaryService.save(activitiSummary);
        return true;
    }

    void assignAndCompleteTask() {
        Map<String, Object> completeVariableMap = new HashMap<>();
        completeVariableMap.put("approvalGroups", "15099777254920,15099777617111,15099777720882");
        completeVariableMap.put("classify", "group");
        completeVariableMap.put("attitude", "agree");
        BpmTaskCommand command = new BpmTaskCommand();
        command.setVariableMap(completeVariableMap);
        boolean b = processWorkFlowService.assignAndCompleteTask(authService.getUserId(), 15102452506780l, command);
        System.out.println(b);
    }
}
