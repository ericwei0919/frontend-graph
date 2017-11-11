import {Component, OnInit} from '@angular/core';
import {BaseComponent} from "../base/base.component";

@Component({
  selector: 'upcoming-tasks',
  template: '<div> <common-task [tasks]="tasks" [todo]="todo" ></common-task></div>'
})
export class UpcomingTasksComponent extends BaseComponent implements OnInit {

  ngOnInit():void {
    this.httpService.get("task/todo").subscribe(res => {
      this.tasks = res.content.data;
    });
  }
  todo:boolean=true;
  tasks:any = []
}
