package com.lmml.graph.common.activiti.impl;

import com.lmml.graph.domain.authority.RbacGroup;
import com.lmml.graph.service.authority.RbacGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WorkFlowQueryService {

    @Autowired
    private RbacGroupService rbacGroupService;

    public List<String> findByActivitiId(String activitiId) {
        List<String> groupIds = new ArrayList<String>();
        List<RbacGroup> groups = rbacGroupService.findGroupByActivitiId(activitiId);
        groups.forEach(rbacGroup -> {
            groupIds.add(String.valueOf(rbacGroup.getGroupId()));
        });
        return Arrays.asList("kermit", "gonzo", "fozzie");
    }

    public List<String> showUsers(String approvers) {
        List<String> users = new ArrayList<String>();
        String[] approver = approvers.split(",");
        for (int index = 0, length = approver.length; index < length; index++) {
            users.add(approver[index]);
        }
        return users;
    }

    public List<String> showGroups(String approvalGroups) {
        List<String> groupIds = new ArrayList<String>();
        String[] approvalGroup = approvalGroups.split(",");
        for (int index = 0, length = approvalGroup.length; index < length; index++) {
            groupIds.add(approvalGroup[index]);
        }
        return groupIds;
    }
}
