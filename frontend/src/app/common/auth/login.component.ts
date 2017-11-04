import {Component, OnInit} from '@angular/core';
import {BaseComponent} from "../../base/base.component";
declare var $:any;
@Component({
  selector: 'login',
  templateUrl: 'login.template.html'
})
export class LoginComponent extends BaseComponent implements OnInit {

  title : any= "demo";
  ngOnInit(): void {

  }
  open() {
    this.httpService.get("user").subscribe(res => {
      this.title = res.content;
    });
  }


}
