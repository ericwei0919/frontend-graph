package com.lmml.graph.service.graph.impl;

import com.lmml.graph.domain.activiti.ActivitiSummary;
import com.lmml.graph.dto.echarts.EchartsData;
import com.lmml.graph.dto.echarts.EchartsSeriesData;
import com.lmml.graph.service.activiti.ActivitiSummaryService;
import com.lmml.graph.service.graph.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GraphServiceImpl implements GraphService {

    @Autowired
    private ActivitiSummaryService activitiSummaryService;

    @Override
    public EchartsData getTaskPie() {
        List<ActivitiSummary> allActivitis = activitiSummaryService.getAll();
        List<Object> legend = new ArrayList<>();
        List<Object> seriesType = new ArrayList<>();
        Map<String,Integer> legendMap = new HashMap<>();
        for (ActivitiSummary activitiSummary : allActivitis) {
            String activitiType = activitiSummary.getActivitiType();
            Integer integer = legendMap.get(activitiType) ;
            if ( null == integer ) {
                integer  = 0;
            }
            integer ++;
            legendMap.put(activitiType,integer);
        }
        for (Map.Entry<String, Integer> entry : legendMap.entrySet()) {
            legend.add(entry.getKey());
            EchartsSeriesData seriesData = new EchartsSeriesData();
            seriesData.setName(entry.getKey());
            seriesData.setValue(entry.getValue());
            seriesType.add(seriesData);
        }
        EchartsData echartsData = new EchartsData();
        List<List<Object>> seriesData = new ArrayList<>();
        seriesData.add(seriesType);
        echartsData.setSeriesData(seriesData);
        List<List<Object>> legendData = new ArrayList<>();
        legendData.add(legend);
        echartsData.setLegendData(legendData);
        return echartsData;
    }
}
