package com.lmml.graph.service.authority;

import com.lmml.graph.domain.authority.RbacGroup;

import java.util.List;

public interface RbacGroupService {
    List<RbacGroup> findGroup();

    List<RbacGroup> save(List<RbacGroup> groups);

    List<RbacGroup> findGroupByActivitiId(String activitiId);
}
