package com.lmml.graph.common.activiti.beans;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PagingTask<T> {
    private int index;
    private int total;
    List<T> data = new ArrayList<>();
}
