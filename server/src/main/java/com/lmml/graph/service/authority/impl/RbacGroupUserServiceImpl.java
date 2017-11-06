package com.lmml.graph.service.authority.impl;

import com.lmml.graph.domain.authority.RbacGroupUser;
import com.lmml.graph.repository.authority.RbacGroupUserRepository;
import com.lmml.graph.service.authority.RbacGroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RbacGroupUserServiceImpl implements RbacGroupUserService {

    @Autowired
    private RbacGroupUserRepository rbacGroupUserRepo;

    @Override
    public List<RbacGroupUser> findGroupUsers() {
        return (List<RbacGroupUser>) rbacGroupUserRepo.findAll();
    }

    @Override
    public List<RbacGroupUser> save(List<RbacGroupUser> rbacGroupUsers) {
        return (List<RbacGroupUser>) rbacGroupUserRepo.save(rbacGroupUsers);
    }
}
