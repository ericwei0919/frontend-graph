package com.lmml.graph.service.authority.impl;

import com.lmml.graph.domain.authority.RbacGroupUser;
import com.lmml.graph.domain.authority.RbacUser;
import com.lmml.graph.repository.authority.RbacGroupUserRepository;
import com.lmml.graph.service.authority.RbacGroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<RbacUser> findGroupUsersByGroupId(Long groupId) {
        List<RbacUser> users = new ArrayList<>();
        List<RbacGroupUser> groupUsers = rbacGroupUserRepo.findByGroupId(groupId);
        groupUsers.forEach(rbacGroupUser -> {
            users.add(rbacGroupUser.getUser());
        });
        return users;
    }

    @Override
    public int removeUsersFromGroup(List<Long> reIds) {
        Iterable<RbacGroupUser> needRemoves = rbacGroupUserRepo.findAll(reIds);
        rbacGroupUserRepo.delete(needRemoves);
        return ((List<RbacGroupUser>)needRemoves).size();
    }
}
