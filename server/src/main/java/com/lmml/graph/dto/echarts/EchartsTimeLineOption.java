package com.lmml.graph.dto.echarts;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EchartsTimeLineOption {

    private List<List<Object>> xAxisData = new ArrayList<>();
    private List<EchartsTimeLineData> optionsData = new ArrayList<>();

    @Data
    public class EchartsTimeLineData {
        private String title;
        private List<SeriesData> series = new ArrayList<>();

        public EchartsTimeLineData() {

        }

        public EchartsTimeLineData(String title, List<SeriesData> series) {
            this.series = series;
            this.title = title;
        }
    }

    @Data
    public class SeriesData {
        List<EchartsSeriesData> data = new ArrayList<>();

        public SeriesData() {

        }

        public SeriesData(List<EchartsSeriesData> data) {
            this.data = data;
        }
    }

}
