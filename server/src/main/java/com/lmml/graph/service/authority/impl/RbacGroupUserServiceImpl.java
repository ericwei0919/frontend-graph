package com.lmml.graph.service.authority.impl;

import com.lmml.graph.common.activiti.ProcessWorkFlowService;
import com.lmml.graph.common.interceptor.AuthService;
import com.lmml.graph.common.util.IdentifierUtil;
import com.lmml.graph.common.util.WorkflowConst;
import com.lmml.graph.domain.activiti.ActivitiSummary;
import com.lmml.graph.domain.authority.RbacGroup;
import com.lmml.graph.domain.authority.RbacGroupUser;
import com.lmml.graph.domain.authority.RbacUser;
import com.lmml.graph.repository.authority.RbacGroupUserRepository;
import com.lmml.graph.repository.authority.RbacUserRepository;
import com.lmml.graph.service.activiti.ActivitiSummaryService;
import com.lmml.graph.service.authority.RbacGroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class RbacGroupUserServiceImpl implements RbacGroupUserService {

    @Autowired
    private RbacGroupUserRepository rbacGroupUserRepo;

    @Autowired
    private RbacUserRepository rbacUserRepo;

    @Autowired
    private ActivitiSummaryService activitiSummaryService;

    @Autowired
    private ProcessWorkFlowService processWorkFlowService;

    @Autowired
    private AuthService authService;

    @Override
    public List<RbacGroupUser> findGroupUsers() {
        return (List<RbacGroupUser>) rbacGroupUserRepo.findAll();
    }

    @Override
    public List<RbacGroupUser> save(List<RbacGroupUser> rbacGroupUsers) {
        rbacGroupUsers.forEach(rbacGroupUser -> {
            rbacGroupUser.setId(IdentifierUtil.generateID());
        });
        startActivit(rbacGroupUsers.get(0).getId());
        return (List<RbacGroupUser>) rbacGroupUserRepo.save(rbacGroupUsers);
    }

    @Override
    public List<RbacGroupUser> findGroupUsersByGroupId(Long groupId) {
        List<RbacUser> users = new ArrayList<>();
        List<RbacGroupUser> groupUsers = rbacGroupUserRepo.findByGroupId(groupId);
        return groupUsers;
    }

    @Override
    public int removeUsersFromGroup(List<Long> reIds) {
        Iterable<RbacGroupUser> needRemoves = rbacGroupUserRepo.findAll(reIds);
        rbacGroupUserRepo.delete(needRemoves);
        return ((List<RbacGroupUser>)needRemoves).size();
    }

    @Override
    public List<RbacGroupUser> findGroupUsersByUserId(Long userId) {
        return rbacGroupUserRepo.findByUserId(userId);
    }

    @Override
    public List<RbacUser> findOtherUsersByGroupId(Long groupId) {
        return rbacUserRepo.findOtherUsersByGroupId(groupId);
    }
    boolean startActivit(Long actBusinessId) {
        Iterable<RbacUser> all = rbacUserRepo.findAll();
        StringBuffer approvers = new StringBuffer();
        all.forEach(rbacUser -> {
            approvers.append(String.valueOf(rbacUser.getUserId()) + ",");
        });
        approvers.deleteCharAt(approvers.length() - 1);
        long activitiSummaryId = IdentifierUtil.generateID();
        Map<String, Object> variableMap = new HashMap<>();
        variableMap.put("approvers", approvers.toString());
        variableMap.put("classify", "user");
        variableMap.put(WorkflowConst.KEY_ACT_THREA_CODE, activitiSummaryId);
        variableMap.put("applyer", String.valueOf(authService.getUserId()));
        String processInstanceId = processWorkFlowService.start("activiti_designated_approval", variableMap);
        ActivitiSummary activitiSummary = new ActivitiSummary();
        RbacUser assignee = new RbacUser();
        assignee.setUserId(authService.getUserId());
        activitiSummary.setAssignee(assignee);
        activitiSummary.setCreateTimestamp(new Timestamp(new Date().getTime()));
        activitiSummary.setActivitiType("组员新增");
        activitiSummary.setHostObjId(actBusinessId);
        activitiSummary.setProcessInstanceId(processInstanceId);
        activitiSummary.setTaskStatus("提交申请");
        activitiSummary.setTableName("rbac_group_user_re");
        activitiSummary.setId(activitiSummaryId);
        activitiSummaryService.save(activitiSummary);
        return true;
    }
}
