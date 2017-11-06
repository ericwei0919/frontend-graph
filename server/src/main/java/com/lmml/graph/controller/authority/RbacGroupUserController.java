package com.lmml.graph.controller.authority;

import com.lmml.graph.common.util.ResponseWrapper;
import com.lmml.graph.domain.authority.RbacGroupUser;
import com.lmml.graph.domain.authority.RbacUser;
import com.lmml.graph.service.authority.RbacGroupUserService;
import com.lmml.graph.service.authority.RbacUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groupUser")
public class RbacGroupUserController {

    @Autowired
    private RbacGroupUserService rbacGroupUserService;


    @GetMapping
    public ResponseWrapper findGroupUsers() throws Exception {
        List<RbacGroupUser> info = rbacGroupUserService.findGroupUsers();
        return ResponseWrapper.success(info);
    }

    @PostMapping
    public ResponseWrapper addGroupUsers(@RequestBody List<RbacGroupUser> rbacGroupUsers) throws Exception {
        List<RbacGroupUser> info = rbacGroupUserService.save(rbacGroupUsers);
        return ResponseWrapper.success(info);
    }


}
