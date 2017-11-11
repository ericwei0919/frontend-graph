import {Component, OnInit} from '@angular/core';
import {BaseComponent} from "../../base/base.component";
import { BsModalRef } from 'ngx-bootstrap/modal/bs-modal-ref.service';
import 'rxjs/add/observable/of';

@Component({
  selector: 'add-group',
  templateUrl: 'add.group.template.html'
})
export class AddGroupComponent extends BaseComponent implements OnInit{

  bsModalRef:BsModalRef;
  groupName:any = "";
  selectedValue:any ={};

  ngOnInit():void {

  }

  activitys:any[] = [
    {id:'act_pa',name:'身份A'},
    {id:'act_pb',name:'身份B'},
    {id:'act_pc',name:'身份C'},
    {id:'act_pd',name:'身份D'},
    {id:'act_pe',name:'身份E'},
    {id:'act_pf',name:'身份F'},
    {id:'act_pg',name:'身份G'},
    {id:'act_ph',name:'身份H'}
  ];
  addGroup() {
    if (this.selectedValue&&this.groupName) {
      let groups = [{groupName: this.groupName,activitiId: this.selectedValue.id}];
      this.httpService.post("group", groups).subscribe(res => {
          this.bsModalRef.hide();
      });
    }
  }

}
