import {Component} from '@angular/core';
import {Md5} from 'ts-md5/dist/md5';
import {HttpService} from "../common/http.service";
import {Router} from "@angular/router";

@Component({
  selector: 'login',
  templateUrl: './login.template.html',
  styleUrls: ['./login.component.css'],
  providers: [Md5]
})
export class LoginComponent {
  user = {'loginId': '', 'password': ''};

  constructor(private md5:Md5,
              private httpService:HttpService,
              private router:Router) {
    localStorage.clear();
  }

  doLogin() {
    this.httpService.post('/user/login',
      {'loginId': this.user.loginId, 'password': Md5.hashStr(this.user.password)}
      ).subscribe(res => {
      localStorage.setItem("token", res.options.token);
      this.router.navigate(['setting/user']);
    });
  }
}
