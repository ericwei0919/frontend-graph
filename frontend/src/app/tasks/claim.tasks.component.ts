import {Component, OnInit} from '@angular/core';
import {BaseComponent} from "../base/base.component";

@Component({
  selector: 'claim-tasks',
  templateUrl: 'claim.tasks.template.html'
})
export class ClaimTasksComponent extends BaseComponent implements OnInit {

  selectedValue:any = {};
  content:string;
  taskTypes:any[] = [];
  groups:any = [];
  users:any = [];
  groupSelectedShow:string;
  selected:any = {};
  userSelectedShow:string;
  typeaheadNoResults:any = false;
  classify:string = 'user';
  approvalList:any[] = [];

  ngOnInit():void {
    this.taskTypes = [
      {id: 'task_pa', name: '采购任务'},
      {id: 'task_pb', name: '入库任务'},
      {id: 'task_pc', name: '出库任务'},
      {id: 'task_pd', name: '报废任务'},
      {id: 'task_pe', name: '人事任务'},
      {id: 'task_pf', name: '合同任务'},
      {id: 'task_pg', name: '请假任务'},
      {id: 'task_ph', name: '其他任务'}
    ];
    this.selectedValue = this.taskTypes[0];
    this.queryGroup();
    this.queryUser();
  }

  postATask() {
    if (this.approvalList && this.approvalList.length > 0) {
      let param = this.getApprovalsObj();
      param['variableMap'].attitude = "agree";
      let obj = {
        bpmTaskCommand:param,
        templateName:this.selectedValue.name,
        content:this.content
      };
      this.httpService.post("template",obj).subscribe(res => {
        this.content = '';
        this.approvalList = [];
        this.queryGroup();
        this.queryUser();
      });
    }
  }

  queryGroup() {
    let queryThis = this;
    queryThis.groups = [];
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

  queryUser() {
    let queryThis = this;
    queryThis.users = [];
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

  typeaheadOnSelect(selected):void {
    this.typeaheadNoResults = true;
    this.selected = selected;
  }

  addApproval() {
    if (this.typeaheadNoResults) {
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

  onClassifyChange(e) {
    this.approvalList = [];
  }


  removeApproval(item) {
    this.approvalList.splice(this.approvalList.indexOf(item), 1);
    if (this.classify == 'user') {
      this.users.push(item);
    }
    if (this.classify == 'group') {
      this.groups.push(item);
    }
  }

  getApprovalsObj() {
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
      param['variableMap'].approvers = approvals;
    }
    if (this.classify == 'group') {
      param['variableMap'].approvalGroups = approvals;
    }
    return param;
  }
}
