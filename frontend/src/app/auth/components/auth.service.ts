import {Injectable} from '@angular/core';
import {BaseOption, BaseService} from '../../common/base.service';
import {HttpHeaders} from '@angular/common/http';

@Injectable()
export class AuthService {

    readonly resource: string = 'user';

    constructor(private baseService: BaseService) {
    }

    doLogin(body: any, callback) {
        return this.baseService.post(this.resource, {param: 'login'}, body, callback);
    }

    doLoginHeaderHttp(body: any, callback) {
        let headerHttp = new HttpHeaders();
        headerHttp = headerHttp.append('Content-Type', 'application/octet-stream');
        headerHttp = headerHttp.append('Authorization', 'Basic YW5ndWxhcjphbmd1bGFygggggggggggggggg');
        let baseOption: BaseOption = {
            httpHeader: headerHttp,
            autoSpin: true,
            openSpin: true,
            closeSpin: true,
            showToastr: true
        };
        return this.baseService.post(this.resource, {param: 'login'}, body, callback, baseOption);
    }
}
