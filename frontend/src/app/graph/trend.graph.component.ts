import {Component, OnDestroy, OnInit} from '@angular/core';
import {BaseComponent} from "../base/base.component";

declare var $:any;
@Component({
  selector: 'trend-graph',
  templateUrl: 'trend.graph.template.html'
})
export class TrendGraphComponent extends BaseComponent implements OnDestroy,OnInit {
  ngOnDestroy(): void {
    clearInterval(this.interval);
  }



  interval;
  ngOnInit():void {
    this.getPie();
    this.interval = setInterval(() => {
      this.getPie();
    }, 10000);
  }

  dataset:any=[];
  legendData:any=[];

  getPie() {
    this.httpService.get("/graph/taskPie").subscribe(res => {
      this.dataset = res.content.seriesData;
      this.chartOption = Object.assign({}, this.chartOption);
      this.chartOption.legend.data = res.content.legendData[0];
    });
  }

  chartOption :any = {
    backgroundColor: '#21202D',
    title : {
      text: '工作流类别占比',
      x:'center',
      textStyle: {
        color: '#aaa',
        fontWeight: 'normal',
        fontSize: 20
      }
    },
    tooltip : {
      trigger: 'item',
      formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
      orient: 'vertical',
      x: 'left',
      data:this.legendData,
      inactiveColor: '#777',
      textStyle: {
        color: '#fff'
      },
      show:false
    },
    toolbox: {
      show : false,
      feature : {
        mark : {show: true},
        dataView : {show: true, readOnly: false},
        magicType : {
          show: true,
          type: ['pie', 'funnel']
        },
        restore : {show: true},
        saveAsImage : {show: true}
      }
    },
    calculable : true,
    series : [
      {
        name:'工作流类别占比',
        type:'pie',
        radius : [0, 200],
        center : ['50%', '50%'],
        roseType : 'area',
        labelLine: {
          normal: {
            length :5 ,
            length2:5
          }
        },
        itemStyle : {
          normal : {
            color: function (params) {
              var colorList = [
                '#FC97AF','#87F7CF','#F7F494','#72CCFF','#F7C5A0',
                '#E9B4FF','#E7FFB6','#81FFFF','#FC97AF','#87F7CF'
              ];
              return colorList[params.dataIndex];
            }
          }
        },
        data:this.dataset
      }
    ]
  };
}
