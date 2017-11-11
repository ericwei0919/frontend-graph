package com.lmml.graph.service.authority.impl;

import com.lmml.graph.common.util.IdentifierUtil;
import com.lmml.graph.domain.authority.RbacGroupUser;
import com.lmml.graph.domain.authority.RbacUser;
import com.lmml.graph.repository.authority.RbacGroupUserRepository;
import com.lmml.graph.repository.authority.RbacUserRepository;
import com.lmml.graph.service.authority.RbacGroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RbacGroupUserServiceImpl implements RbacGroupUserService {

    @Autowired
    private RbacGroupUserRepository rbacGroupUserRepo;

    @Autowired
    private RbacUserRepository rbacUserRepo;


    @Override
    public List<RbacGroupUser> findGroupUsers() {
        return (List<RbacGroupUser>) rbacGroupUserRepo.findAll();
    }

    @Override
    public List<RbacGroupUser> save(List<RbacGroupUser> rbacGroupUsers) {
        rbacGroupUsers.forEach(rbacGroupUser -> {
            rbacGroupUser.setId(IdentifierUtil.generateID());
        });
        return (List<RbacGroupUser>) rbacGroupUserRepo.save(rbacGroupUsers);
    }

    @Override
    public List<RbacGroupUser> findGroupUsersByGroupId(Long groupId) {
        List<RbacUser> users = new ArrayList<>();
        List<RbacGroupUser> groupUsers = rbacGroupUserRepo.findByGroupId(groupId);
        return groupUsers;
    }

    @Override
    public int removeUsersFromGroup(List<Long> reIds) {
        Iterable<RbacGroupUser> needRemoves = rbacGroupUserRepo.findAll(reIds);
        rbacGroupUserRepo.delete(needRemoves);
        return ((List<RbacGroupUser>)needRemoves).size();
    }

    @Override
    public List<RbacGroupUser> findGroupUsersByUserId(Long userId) {
        return rbacGroupUserRepo.findByUserId(userId);
    }

    @Override
    public List<RbacUser> findOtherUsersByGroupId(Long groupId) {
        return rbacUserRepo.findOtherUsersByGroupId(groupId);
    }
}
