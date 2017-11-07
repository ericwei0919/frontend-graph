import {Component, OnInit} from '@angular/core';
import {BaseComponent} from "../base/base.component";

declare var $:any;
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
