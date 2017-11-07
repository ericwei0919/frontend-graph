import {Component, OnInit} from '@angular/core';
import {BaseComponent} from "../base/base.component";

declare var $:any;
@Component({
  selector: 'user',
  templateUrl: 'user.template.html'
})
export class UserComponent extends BaseComponent implements OnInit {

  title:any = "";
  userName:any = "";
  users:any = [];

  ngOnInit():void {

  }

  query() {
    this.httpService.get("user").subscribe(res => {
      this.users = res.content;
    });
  }

  addUser() {
    if (this.userName) {
      let users = [{userName: this.userName}];
      this.httpService.post("user", users).subscribe(res => {
      });
    }
  }

}
