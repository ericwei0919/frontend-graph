package com.lmml.graph.service.activiti.impl;

import com.lmml.graph.common.interceptor.AuthService;
import com.lmml.graph.domain.activiti.ActivitiSummary;
import com.lmml.graph.repository.activiti.ActivitiSummaryRepository;
import com.lmml.graph.service.activiti.ActivitiSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivitiSummaryServiceImpl implements ActivitiSummaryService {

    @Autowired
    private AuthService authService;

    @Autowired
    private ActivitiSummaryRepository activitiSummaryRepo;

    @Override
    public ActivitiSummary findById(Long id) {
        ActivitiSummary one = activitiSummaryRepo.findById(id);
        return one;
    }

    @Override
    public ActivitiSummary save(ActivitiSummary activitiSummary) {
        return activitiSummaryRepo.save(activitiSummary);
    }

    @Override
    public List<ActivitiSummary> save(List<ActivitiSummary> activitiSummarys) {
        return (List<ActivitiSummary> )activitiSummaryRepo.save(activitiSummarys);
    }
}
