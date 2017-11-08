package com.lmml.graph.service.authority.impl;

import com.lmml.graph.common.util.IdentifierUtil;
import com.lmml.graph.domain.authority.RbacGroup;
import com.lmml.graph.repository.authority.RbacGroupRepository;
import com.lmml.graph.service.authority.RbacGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class RbacGroupServiceImpl implements RbacGroupService {

    @Autowired
    private RbacGroupRepository rbacGroupRepo;

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
        return (List<RbacGroup>) rbacGroupRepo.save(groups);
    }

    @Override
    public List<RbacGroup> findGroupByActivitiId(String activitiId) {
        return rbacGroupRepo.findGroupByActivitiId(activitiId);
    }
}
