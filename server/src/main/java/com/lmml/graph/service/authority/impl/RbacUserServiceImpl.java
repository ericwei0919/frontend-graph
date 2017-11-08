package com.lmml.graph.service.authority.impl;

import com.lmml.graph.common.exception.ApplicationException;
import com.lmml.graph.common.util.IdentifierUtil;
import com.lmml.graph.common.util.ResponseWrapper;
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
    public ResponseWrapper<RbacUser> doLogin(RbacUser user) {
        String loginPassword = user.getPassword();
        user = rbacUserRepo.findByLoginId(user.getLoginId());
        String password = user.getPassword();
        if (!password.equals(loginPassword)){
            throw new ApplicationException("登录失败！");
        }
        ResponseWrapper<RbacUser> wrapper = new ResponseWrapper<>();
        wrapper.setContent(user);
        wrapper.addOption("token", generateToken(user));
        return wrapper;
    }

    public String generateToken(RbacUser dto) {
        LocalDateTime currentTime = LocalDateTime.now();
        return Jwts.builder()
                .setSubject(String.valueOf(dto.getUserId()))
                .claim("userName", dto.getUserName())
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentTime
                        .plusMinutes(new Date().getTime())
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256, "air_secretKey")
                .compact();
    }
}
