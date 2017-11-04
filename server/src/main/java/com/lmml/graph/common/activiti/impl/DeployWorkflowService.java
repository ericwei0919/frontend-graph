package com.lmml.graph.common.activiti.impl;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Service
public class DeployWorkflowService {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    private ResourceLoader resourceLoader;

    private boolean deploy(String processDefinitionKey, InputStream bpmnFile) {
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey);
        if (query.list().isEmpty()) {
            repositoryService.createDeployment().addInputStream(processDefinitionKey + ".bpmn20.xml", bpmnFile).deploy();
        }
        return true;
    }

    @PostConstruct
    void init() {
        this.deployABpmn();
    }

    private boolean deployABpmn() {
        try {
            Resource resource = resourceLoader.getResource("classpath:diagrams/userApplyIdentityV20171013_01.bpmn");
            this.deploy("userApplyIdentityV20171013_01", resource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean deployBBpmn() {
        return true;
    }

    private boolean deployCBpmn() {
        return false;
    }

    private boolean deployDBpmn() {
        return false;
    }
}
