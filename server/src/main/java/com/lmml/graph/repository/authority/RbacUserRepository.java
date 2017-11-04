package com.lmml.graph.repository.authority;

import com.lmml.graph.domain.authority.RbacUser;
import com.lmml.graph.repository.base.PagingAndSpecificationRepository;

public interface RbacUserRepository extends PagingAndSpecificationRepository<RbacUser,Long> {

    boolean deleteByUserId(String id);
}
