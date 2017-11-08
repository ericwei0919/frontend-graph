import {Component, OnInit} from '@angular/core';
import {BaseComponent} from "../base/base.component";

declare var $:any;
@Component({
  selector: 'trend-graph',
  templateUrl: 'trend.graph.template.html'
})
export class TrendGraphComponent extends BaseComponent implements OnInit {

  ngOnInit():void {

  }

}
