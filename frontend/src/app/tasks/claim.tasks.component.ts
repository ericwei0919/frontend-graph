import {Component, OnInit} from '@angular/core';
import {BaseComponent} from "../base/base.component";

@Component({
  selector: 'claim-tasks',
  template: '<div> <common-task [tasks]="tasks" [todo]="todo" ></common-task></div>'
})
export class ClaimTasksComponent extends BaseComponent implements OnInit {

  ngOnInit():void {

  }

  todo:boolean = false;
  tasks:any = [
    {
      actBusinessId: 'actBusinessId',
      applicantId: 'applicantId',
      applicantName: 'applicantName',
      applicantType: 'applicantType',
      applicantTypeName: 'applicantTypeName',
      applicantTime: 'applicantTime',
      lastProcessingTime: 'lastProcessingTime',
      previousApproverId: 'previousApproverId',
      previousApproverName: 'previousApproverName',
      previousApprovertatus: 'previousApprovertatus'
    }
  ]
}
