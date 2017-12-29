package com.lmml.graph.controller.graph;

import com.lmml.graph.common.util.ResponseWrapper;
import com.lmml.graph.dto.echarts.EchartsData;
import com.lmml.graph.service.graph.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/graph")
public class GraphController {

    @Autowired
    private GraphService graphService;


    @GetMapping("/taskPie")
    public ResponseWrapper getTaskPie() throws Exception {
        EchartsData pie = graphService.getTaskPie();
        return ResponseWrapper.success(pie);
    }


}
