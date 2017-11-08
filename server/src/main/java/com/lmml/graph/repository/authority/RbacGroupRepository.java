package com.lmml.graph.repository.authority;

import com.lmml.graph.domain.authority.RbacGroup;
import com.lmml.graph.domain.authority.RbacUser;
import com.lmml.graph.repository.base.PagingAndSpecificationRepository;

import java.util.List;

public interface RbacGroupRepository extends PagingAndSpecificationRepository<RbacGroup,Long> {

    boolean deleteByGroupId(String id);

    List<RbacGroup> findGroupByActivitiId(String activitiId);
}
