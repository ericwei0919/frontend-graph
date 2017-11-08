import {Component, OnInit} from '@angular/core';
import {BaseComponent} from "../base/base.component";

declare var $:any;

@Component({
  selector: 'group-user',
  templateUrl: 'group.user.template.html'
})
export class GroupUserComponent extends BaseComponent implements OnInit {



  groups:any = [];
  users:any = [];
  groupUsers:any = [];

  ngOnInit():void {

  }

  addGroupUser() {
    this.httpService.post("groupUser", []).subscribe(res => {

    });
  };

  queryUser() {
    this.httpService.get("user").subscribe(res => {
      this.users = res.content;
    });
  };

  queryGroup() {
    this.httpService.get("group").subscribe(res => {
      this.groups = res.content;
    });
  };

  queryGroupUser() {
    this.httpService.get("groupUser/groupId").subscribe(res => {
      this.groupUsers = res.content;
    });
  };

}
