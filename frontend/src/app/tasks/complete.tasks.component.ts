import {Component, OnInit} from '@angular/core';
import {BaseComponent} from "../base/base.component";

@Component({
  selector: 'complete-tasks',
  template: '<div> <common-task [tasks]="tasks" (bsModalChange)="bsModalChange($event)" [todo]="todo" ></common-task></div>'
})
export class CompleteTasksComponent extends BaseComponent implements OnInit {

  ngOnInit():void {
    this.getCompletedTask();
  }

  getCompletedTask() {
    this.httpService.get('task/completed').subscribe(res => {
      this.tasks = res.content.data;
    });
  }

  todo:boolean = false;
  tasks:any = [];

  bsModalChange(event) {
    console.log(event);
    this.getCompletedTask();
  }
}
