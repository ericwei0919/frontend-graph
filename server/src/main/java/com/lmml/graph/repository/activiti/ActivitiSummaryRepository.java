package com.lmml.graph.repository.activiti;

import com.lmml.graph.domain.activiti.ActivitiSummary;
import com.lmml.graph.repository.base.PagingAndSpecificationRepository; 

public interface ActivitiSummaryRepository extends PagingAndSpecificationRepository<ActivitiSummary,Long> {

    ActivitiSummary findById(Long id);
}
