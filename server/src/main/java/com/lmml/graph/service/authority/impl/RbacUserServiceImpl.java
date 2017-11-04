package com.lmml.graph.service.authority.impl;
import com.lmml.graph.service.authority.RbacUserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RbacUserServiceImpl implements RbacUserService {


    @Override
    public String findUser() {
        return "this is a demo ";
    }
}
