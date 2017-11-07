package com.lmml.graph.service.authority.impl;

import com.lmml.graph.common.util.IdentifierUtil;
import com.lmml.graph.domain.authority.AuthToken;
import com.lmml.graph.domain.authority.RbacUser;
import com.lmml.graph.repository.authority.RbacUserRepository;
import com.lmml.graph.service.authority.RbacUserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class RbacUserServiceImpl implements RbacUserService {

    @Autowired
    private RbacUserRepository rbacUserRepo;

    @Autowired
    RepositoryService repositoryService;

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

    @Override
    public AuthToken doLogin(RbacUser user) {
        return generateToken(user);
    }
    private final SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
    private AuthToken generateToken(RbacUser dto) {
        String token = Jwts.builder().setSubject(String.valueOf(dto.getUserId()))
                .claim("attribute", dto).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
        AuthToken authToken = new AuthToken(token);
        return authToken;
    }
}
