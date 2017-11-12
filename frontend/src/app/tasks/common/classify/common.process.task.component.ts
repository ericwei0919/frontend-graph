import {Component, OnInit} from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal/bs-modal-ref.service';
import {BaseComponent} from "../../../base/base.component";

@Component({
  selector: 'common-process-task',
  templateUrl: 'common.process.task.template.html'
})
export class CommonProcessTaskComponent extends BaseComponent implements OnInit {
  bsModalRef:BsModalRef;

  task:any = {};
  groups:any = [];

  users:any = [];

  ngOnInit():void {
    this.queryGroup();
    this.queryUser();
  }

  groupSelectedShow:string;
  selected:any = {};

  queryGroup() {
    let queryThis = this;
    this.httpService.get("group").subscribe(res => {
      res.content.forEach(function (group) {
        let newGroup = {
          approvalId: group.groupId,
          approvalName: group.groupName,
          fieldName: group.groupName + "(" + group.activitiId + ")"
        };
        queryThis.groups.push(newGroup);
      });
    });
  }

  userSelectedShow:string;

  queryUser() {
    let queryThis = this;
    this.httpService.get("user").subscribe(res => {
      res.content.forEach(function (user) {
        let newUser = {
          approvalId: user.userId,
          approvalName: user.userName,
          fieldName: user.userName + "(" + user.loginId + ")"
        };
        queryThis.users.push(newUser);
      });
    });
  }

  changeTypeaheadNoResults(e:boolean):void {
    this.typeaheadNoResults = false;
  }

  typeaheadNoResults:any = false;

  typeaheadOnSelect(selected):void {
    this.typeaheadNoResults = true;
    this.selected = selected;
  }

  classify:string = 'user';

  addApproval() {
    if (this.typeaheadNoResults){
      this.typeaheadNoResults = false;
      this.userSelectedShow = '';
      this.groupSelectedShow = '';
      if (this.classify == 'user') {
        this.users.splice(this.users.indexOf(this.selected.item), 1);
      }
      if (this.classify == 'group') {
        this.groups.splice(this.groups.indexOf(this.selected.item), 1);
      }
      this.approvalList.push(this.selected.item);
    }
  }

  approvalList:any[] = [];

  onClassifyChange(e) {
    this.approvalList = [];
  }


  removeApproval(item){
    this.approvalList.splice(this.approvalList.indexOf(item), 1);
    if (this.classify == 'user') {
      this.users.push(item);
    }
    if (this.classify == 'group') {
      this.groups.push(item);
    }
  }

  getApprovalsObj(){
    let approvals = "";
    this.approvalList.forEach(function (approval) {
      if (approvals != "") {
        approvals += ",";
      }
      approvals += approval.approvalId;
    });
    let param = {};
    param['variableMap'] = {
      "classify": this.classify,
      "attitude": "agree"
    };
    if (this.classify == 'user') {
      param['variableMap'].approvers= approvals;
    }
    if (this.classify == 'group') {
      param['variableMap'].approvalGroups= approvals;
    }
    return param;
  }

  assignAndCompleteTask() {
    if (this.approvalList && this.approvalList.length > 0 ){
      let param = this.getApprovalsObj();
      param['variableMap'].attitude = "agree";
      this.httpService.post("task/"+this.task.actBusinessId+"/pushTask",param).subscribe(res => {
        this.bsModalRef.hide();
      });
    }

  }

  assignTask(){
    this.httpService.post("task/"+this.task.actBusinessId+"/assign",{}).subscribe(res => {
      this.bsModalRef.hide();
    });
  }

  completeTask(){
    if (this.approvalList && this.approvalList.length > 0 ){
      let param = this.getApprovalsObj();
      param['variableMap'].attitude = "agree";
      this.httpService.post("task/"+this.task.actBusinessId+"/complete",param).subscribe(res => {
        this.bsModalRef.hide();
      });
    }
  }

  finishTask(){
    console.log("finishTask");
    let param = {};
    param['variableMap'] = {
      "attitude": "annulment"
    };
    this.httpService.post("task/"+this.task.actBusinessId+"/pushTask",param).subscribe(res => {
      this.bsModalRef.hide();
    });
  }

}
