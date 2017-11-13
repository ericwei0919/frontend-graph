import {Component, OnInit, TemplateRef } from '@angular/core';
import {BaseComponent} from "../../base/base.component";
import {BsModalRef} from 'ngx-bootstrap/modal/bs-modal-ref.service';
import {GroupUserComponent} from "./group.user.component";
import {AddGroupComponent} from "./add.group.component";

@Component({
  selector: 'group',
  templateUrl: 'group.template.html'
})
export class GroupComponent extends BaseComponent implements OnInit {

  groups:any = [];

  ngOnInit():void {
    this.query();
  }

  bsModalRef:BsModalRef;

  addBsModalRef:BsModalRef;

  openGroupUserWithComponent(group) {
    this.bsModalRef = this.modalService.show(GroupUserComponent);
    this.bsModalRef.content.group = group;
    this.bsModalRef.content.bsModalRef = this.bsModalRef;
  }

  openAddGroupComponent() {
    this.addBsModalRef = this.modalService.show(AddGroupComponent);
    this.addBsModalRef.content.bsModalRef = this.addBsModalRef;
    this.addBsModalRef.content.onClose.subscribe(result => {
      if(result){
        this.query();
      }
    })
  }

  query() {
    this.httpService.get("group").subscribe(res => {
      this.groups = res.content;
    });
  }
}
