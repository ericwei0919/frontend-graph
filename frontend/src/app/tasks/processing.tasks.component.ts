import {Component, OnInit} from '@angular/core';
import {BaseComponent} from "../base/base.component";

@Component({
  selector: 'processing-tasks',
  template: '<div> <common-task [tasks]="tasks" (bsModalChange)="bsModalChange($event)"  [todo]="todo" ></common-task></div>'
})
export class ProcessingTasksComponent extends BaseComponent implements OnInit {

  ngOnInit():void {
    this.getInProgressTask();
  }

  todo:boolean = false;
  tasks:any = [];

  getInProgressTask() {
    this.httpService.get("task/inProgress").subscribe(res => {
      this.tasks = res.content.data;
    });
  } ;

  bsModalChange(event) {
    console.log(event);
    this.getInProgressTask();
  }
}
