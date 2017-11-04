package com.lmml.graph.service.authority.impl;
import com.lmml.graph.common.util.IdentifierUtil;
import com.lmml.graph.domain.authority.RbacUser;
import com.lmml.graph.repository.authority.RbacUserRepository;
import com.lmml.graph.service.authority.RbacUserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class RbacUserServiceImpl implements RbacUserService {

    @Autowired
    private RbacUserRepository rbacUserRepo;

    @Override
    public List<RbacUser> findUser() {
        return (List<RbacUser>) rbacUserRepo.findAll();
    }

    @Override
    public List<RbacUser> save(List<RbacUser> rbacUsers) {
        rbacUsers.forEach(rbacUser -> {
            rbacUser.setUserId(IdentifierUtil.generateID());
            rbacUser.setCreateTimestamp(new Timestamp(new Date().getTime()));
        });
        return (List<RbacUser>) rbacUserRepo.save(rbacUsers);
    }

    @Override
    public boolean deleteById(String id) {
        return rbacUserRepo.deleteByUserId(id);
    }
}
