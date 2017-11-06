package com.lmml.graph.controller.authority;

import com.lmml.graph.common.util.ResponseWrapper;
import com.lmml.graph.domain.authority.RbacGroup;
import com.lmml.graph.service.authority.RbacGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class RbacGroupController {

    @Autowired
    private RbacGroupService groupService;


    @GetMapping
    public ResponseWrapper findGroup() throws Exception {
        List<RbacGroup> info = groupService.findGroup();
        return ResponseWrapper.success(info);
    }

    @PostMapping
    public ResponseWrapper addGroup(@RequestBody List<RbacGroup> groups) throws Exception {
        List<RbacGroup> info = groupService.save(groups);
        return ResponseWrapper.success(info);
    }


}
