import {Component, OnInit} from '@angular/core';
import {BaseComponent} from "../base/base.component";

declare var $:any;
@Component({
  selector: 'processing-tasks',
  templateUrl: 'processing.tasks.template.html'
})
export class ProcessingTasksComponent extends BaseComponent implements OnInit {

  ngOnInit():void {

  }

}
