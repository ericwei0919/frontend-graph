package com.lmml.graph.controller.authority;

import com.lmml.graph.common.util.ResponseWrapper;
import com.lmml.graph.domain.authority.RbacUser;
import com.lmml.graph.service.authority.RbacUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class RbacUserController {

    @Autowired
    private RbacUserService userService;


    @GetMapping
    public ResponseWrapper fingUser() throws Exception {
        List<RbacUser> info = userService.findUser();
        return ResponseWrapper.success(info);
    }

    @PostMapping
    public ResponseWrapper fingUser(@RequestBody List<RbacUser> users) throws Exception {
        List<RbacUser> info = userService.save(users);
        return ResponseWrapper.success(info);
    }


}
