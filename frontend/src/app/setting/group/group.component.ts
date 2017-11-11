import {Component, OnInit, TemplateRef } from '@angular/core';
import {BaseComponent} from "../../base/base.component";
import {BsModalRef} from 'ngx-bootstrap/modal/bs-modal-ref.service';
import {GroupUserComponent} from "./group.user.component";

@Component({
  selector: 'group',
  templateUrl: 'group.template.html'
})
export class GroupComponent extends BaseComponent implements OnInit {
  groupName:any = "";
  activitiId:any = "";
  groups:any = [];

  ngOnInit():void {

  }
  bsModalRef: BsModalRef;

  openGroupUserWithComponent(group) {
    this.bsModalRef = this.modalService.show(GroupUserComponent);
    this.bsModalRef.content.group = group;
    this.bsModalRef.content.bsModalRef = this.bsModalRef;
  }

  addGroup() {
    if (this.activitiId&&this.groupName) {
      let groups = [{groupName: this.groupName,activitiId: this.activitiId}];
      this.httpService.post("group", groups).subscribe(res => {

      });
    }
  }
  query() {
    this.httpService.get("group").subscribe(res => {
      this.groups = res.content;
    });
  }
}
