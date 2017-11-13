import {Component, OnInit} from '@angular/core';
import {BaseComponent} from "../base/base.component";

@Component({
  selector: 'upcoming-tasks',
  template: '<div> <common-task [tasks]="tasks"  (bsModalChange)="bsModalChange($event)" [todo]="todo" ></common-task></div>'
})
export class UpcomingTasksComponent extends BaseComponent implements OnInit {

  ngOnInit():void {
    this.getTodoTask();
  }

  todo:boolean = true;
  tasks:any = [];

  getTodoTask() {
    this.httpService.get("task/todo").subscribe(res => {
      this.tasks = res.content.data;
    });
  } ;

  bsModalChange(event) {
    console.log(event);
    this.getTodoTask();
  }
}
