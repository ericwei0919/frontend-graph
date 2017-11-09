package com.lmml.graph.repository.authority;

import com.lmml.graph.domain.authority.RbacGroupUser;
import com.lmml.graph.domain.authority.RbacUser;
import com.lmml.graph.repository.base.PagingAndSpecificationRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RbacGroupUserRepository extends PagingAndSpecificationRepository<RbacGroupUser,Long> {
    @Query(value = "from RbacGroupUser rgu where rgu.group.groupId = ?1")
    List<RbacGroupUser> findByGroupId(Long groupId);

    @Query(value = "from RbacGroupUser rgu where rgu.user.userId = ?1")
    List<RbacGroupUser> findByUserId(Long userId);
}
