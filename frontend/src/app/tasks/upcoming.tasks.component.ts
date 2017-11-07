import {Component, OnInit} from '@angular/core';
import {BaseComponent} from "../base/base.component";

declare var $:any;
@Component({
  selector: 'upcoming-tasks',
  templateUrl: 'upcoming.tasks.template.html'
})
export class UpcomingTasksComponent extends BaseComponent implements OnInit {

  ngOnInit():void {

  }

}
