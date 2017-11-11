import {Component, OnDestroy,OnInit} from '@angular/core';
import {BaseComponent} from "../../base/base.component";
import { BsModalRef } from 'ngx-bootstrap/modal/bs-modal-ref.service';
import 'rxjs/add/observable/of';

@Component({
  selector: 'group-user',
  templateUrl: 'group.user.template.html'
})
export class GroupUserComponent extends BaseComponent implements OnInit ,OnDestroy{
  ngOnDestroy():void {
    clearInterval(this.interval);
  }

  bsModalRef:BsModalRef;
  group:any = {};
  interval;
  ngOnInit():void {
    this.interval = setInterval(() => {
      if (this.group.groupId){
        clearInterval(this.interval);
        this.queryGroupUser();
        this.queryNotGroupUser();
      }
    }, 500);
  }

  groupUsers:any = [];

  queryGroupUser() {
    this.httpService.get("groupUser/user/"+this.group.groupId).subscribe(res => {
      this.groupUsers = res.content;
    });
  }

  queryNotGroupUser() {
    let queryThis = this;
    this.httpService.get("groupUser/other/"+this.group.groupId).subscribe(res => {
      res.content.forEach(function (user) {
        let newUser = {
          userId: user.userId,
          userName: user.userName,
          userField: user.userName + "(" + user.loginId + ")"
        };
        queryThis.users.push(newUser);
      });
    });
  }

  userSelected:string;
  selected:any = {};
  users:any[] = [];

  addUserToGroup() {
    if (this.typeaheadNoResults){
      this.httpService.post("groupUser",[{group:{groupId:this.group.groupId},user:{userId:this.selected.item.userId}}]).subscribe(res => {
        this.users = [];
        this.userSelected ='';
        this.queryGroupUser();
        this.queryNotGroupUser();
        this.typeaheadNoResults = false;
      });
    }
  }

  remove(item){
    this.httpService.deleteBody("groupUser",[item.id]).subscribe(res => {
      this.users = [];
      this.queryGroupUser();
      this.queryNotGroupUser();
    });
  }
  changeTypeaheadNoResults(e: boolean): void {
    this.typeaheadNoResults = false;
  }
  typeaheadNoResults:any = false;
  typeaheadOnSelect(selected):void {
    this.typeaheadNoResults = true;
    this.selected = selected;
  }
}
