package com.lmml.graph.controller.authority;

import com.lmml.graph.common.util.ResponseWrapper;
import com.lmml.graph.service.authority.RbacUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class RbacUserController {

    @Autowired
    private RbacUserService userService;


    @GetMapping
    public ResponseWrapper fingUser() throws Exception {
        String info = userService.findUser();
        return ResponseWrapper.success("操作成功",info+new Date().getTime());
    }


}
