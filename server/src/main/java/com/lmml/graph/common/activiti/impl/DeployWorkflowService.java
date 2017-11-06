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

    public static final String ACTIVITI_DESIGNATED_APPROVAL = "activiti_designated_approval";
    public static final String ACTIVITI_PROCESS_APPROVAL = "activiti_process_approval";

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
       /* this.deployABpmn();
        this.deployBBpmn();*/
    }

    //todo  Suspending and activating a process

    private boolean deployABpmn() {
        try {
            Resource resource = resourceLoader.getResource("classpath:diagrams/"+ACTIVITI_DESIGNATED_APPROVAL+".bpmn");
            this.deploy(ACTIVITI_DESIGNATED_APPROVAL, resource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean deployBBpmn() {
        try {
            Resource resource = resourceLoader.getResource("classpath:diagrams/"+ACTIVITI_PROCESS_APPROVAL+".bpmn");
            this.deploy(ACTIVITI_PROCESS_APPROVAL, resource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean deployCBpmn() {
        return false;
    }

    private boolean deployDBpmn() {
        return false;
    }
}
