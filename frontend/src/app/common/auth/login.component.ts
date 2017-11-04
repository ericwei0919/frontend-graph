import {Component, OnInit} from '@angular/core';
import {BaseComponent} from "../../base/base.component";
declare var $:any;
@Component({
  selector: 'login',
  templateUrl: 'login.template.html'
})
export class LoginComponent extends BaseComponent implements OnInit {

  title:any = "";
  userName:any = "";
  users:any = [];

  ngOnInit():void {

  }

  open() {
    this.httpService.get("user").subscribe(res => {
      this.users = res.content;
    });
  }

  addUser() {
    if (this.userName) {
      let users = [{userName: this.userName}];
      this.httpService.post("user", users).subscribe(res => {
        this.title = res.content;
      });
    }
  }

}
