package com.lmml.graph.common.activiti.beans;

import java.util.ArrayList;
import java.util.List;

public class PagingTask<T> {

    private int index;
    private int total;
    List<T> tasks = new ArrayList<>();

}
