package com.lmml.graph.repository.authority;

import com.lmml.graph.domain.authority.RbacGroup;
import com.lmml.graph.domain.authority.RbacUser;
import com.lmml.graph.repository.base.PagingAndSpecificationRepository;

public interface RbacGroupRepository extends PagingAndSpecificationRepository<RbacGroup,Long> {

    boolean deleteByGroupId(String id);
}
