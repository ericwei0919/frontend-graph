package com.lmml.graph.service.authority;

import com.lmml.graph.domain.authority.RbacGroupUser;
import com.lmml.graph.domain.authority.RbacUser;

import java.util.List;

public interface RbacGroupUserService {
    List<RbacGroupUser> findGroupUsers();

    List<RbacGroupUser> save(List<RbacGroupUser> rbacGroupUsers);

    List<RbacUser> findGroupUsersByGroupId(Long groupId);

    int removeUsersFromGroup(List<Long> reIds);

}
