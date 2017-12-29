package com.lmml.graph.dto.echarts;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EchartsData {
    private List<List<Object>> seriesData = new ArrayList<>();
    private List<List<Object>> xAxisData = new ArrayList<>();
    private List<List<Object>> legendData = new ArrayList<>();
}
