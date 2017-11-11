package com.lmml.graph.repository.authority;

import com.lmml.graph.domain.authority.RbacUser;
import com.lmml.graph.repository.base.PagingAndSpecificationRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RbacUserRepository extends PagingAndSpecificationRepository<RbacUser,Long> {

    boolean deleteByUserId(String id);

    RbacUser findByLoginId(String loginId);

    @Query(value = "select * from rbac_user rus " +
            "where rus.user_id not in (select user_id " +
            "from rbac_group_user_re where group_id = ?1)",nativeQuery = true)
    List<RbacUser> findOtherUsersByGroupId(Long groupId);

}
