package com.lmml.graph.service.authority;

import com.lmml.graph.domain.authority.RbacUser;

import java.util.List;

public interface RbacUserService {

    List<RbacUser> findUser();

    List<RbacUser> save(List<RbacUser> rbacUsers);

    boolean deleteById(String id);
}
