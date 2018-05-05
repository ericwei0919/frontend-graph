import {Component} from '@angular/core';
import {AuthService} from '../auth.service';

@Component({
    selector: 'login',
    templateUrl: './login.template.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent {

    constructor(private authService: AuthService) {
    }

    login() {
        let users = {loginId: 'loginId', password: 'password'};
        this.authService.doLogin(users,
            function (result) {
                console.log(result);
            });
        this.authService.doLoginHeaderHttp(users,
            function (result) {
                console.log(result);
            });
    }
}
