package com.lmml.graph.repository.base;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

@NoRepositoryBean
public interface PagingAndSpecificationRepository <T, ID extends Serializable> extends PagingAndSortingRepository<T, ID>, JpaSpecificationExecutor {
}
