package com.lmml.graph.service.authority.impl;

import com.lmml.graph.common.activiti.ProcessWorkFlowService;
import com.lmml.graph.common.activiti.beans.BpmTaskCommand;
import com.lmml.graph.common.interceptor.AuthService;
import com.lmml.graph.common.util.IdentifierUtil;
import com.lmml.graph.common.util.WorkflowConst;
import com.lmml.graph.domain.authority.RbacGroup;
import com.lmml.graph.repository.authority.RbacGroupRepository;
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
        this.assignAndCompleteTask();
        return (List<RbacGroup>) rbacGroupRepo.save(groups);
    }

    @Override
    public List<RbacGroup> findGroupByActivitiId(String activitiId) {
        return rbacGroupRepo.findGroupByActivitiId(activitiId);
    }

    boolean startActivit(Long actBusinessId) {
        Map<String, Object> variableMap = new HashMap<>();
        variableMap.put("approvers", "15097999920830,1509799992000,15098000038991,1509800004000,15098000274222");
        variableMap.put("classify", "user");
        variableMap.put(WorkflowConst.KEY_ACT_THREA_CODE, actBusinessId);
        variableMap.put("applyer", String.valueOf(authService.getUserId()));
        String processInstanceId = processWorkFlowService.start("activiti_designated_approval", variableMap);
        System.out.println(processInstanceId);
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
