package com.lmml.graph.service.activiti;

import com.lmml.graph.domain.activiti.ActivitiSummary;

import java.util.List;

public interface ActivitiSummaryService {

    ActivitiSummary findById(Long id);

    ActivitiSummary save(ActivitiSummary activitiSummary);

    List<ActivitiSummary> save(List<ActivitiSummary> activitiSummarys);

}
