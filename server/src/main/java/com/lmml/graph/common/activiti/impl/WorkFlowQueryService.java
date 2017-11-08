package com.lmml.graph.common.activiti.impl;

import com.lmml.graph.domain.authority.RbacGroup;
import com.lmml.graph.service.authority.RbacGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return groupIds;
    }
}
