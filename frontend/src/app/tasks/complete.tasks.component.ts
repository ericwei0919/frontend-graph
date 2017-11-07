import {Component, OnInit} from '@angular/core';
import {BaseComponent} from "../base/base.component";

declare var $:any;
@Component({
  selector: 'complete-tasks',
  templateUrl: 'complete.tasks.template.html'
})
export class CompleteTasksComponent extends BaseComponent implements OnInit {

  ngOnInit():void {

  }

}
