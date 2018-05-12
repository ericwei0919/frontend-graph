import {Injectable} from '@angular/core';
import {RestOption, RestService} from './rest.service';
import {ResourceService} from './resource.service';
import {HttpHeaders} from '@angular/common/http';

export interface BaseOption {
    httpHeader?: HttpHeaders;
    autoSpin?: boolean;
    openSpin?: boolean;
    closeSpin?: boolean;
    showToastr?: boolean;
    toastrTitle?: string;
    toastrMessage?: string;
    toastrType?: string;
}

@Injectable()
export class BaseService {

    constructor(private restService: RestService,
                private resourceService: ResourceService) {
    }

    private structureRestOption(httpUrl: string, body?: any, option?: BaseOption, param?: Object): RestOption {
        let restOption: RestOption = {
            restUrl: httpUrl,
            httpHeader: option ? option.httpHeader : undefined,
            restBody: body,
            restHttpParams: param
        };
        return restOption;
    }

    private callbackTreatOption(option: BaseOption) {
        if (option) {
            if (option.closeSpin || option.autoSpin) {
                console.log('closeSpin');
            }
            if (option.showToastr) {
                console.log('showToastr');
            }
        }
    }

    post(resourceKey: string, param: any, payload: any, callback, option?: BaseOption) {
        let thisInjectable = this;
        this.resourceService.getResource(resourceKey, param, function (httpUrl) {
            if (httpUrl) {
                let restOption = thisInjectable.structureRestOption(httpUrl, payload, option);
                if (option && (option.autoSpin || option.openSpin)) {
                    console.log('openSpin');
                }
                return thisInjectable.restService.post(restOption).subscribe(result => {
                    callback && callback.call(callback, result);
                    thisInjectable.callbackTreatOption(option);
                });
            } else {
                console.log('error');
            }
        });
    }

    get(resourceKey: string, param: any, callback, option?: BaseOption) {
        let thisInjectable = this;
        this.resourceService.getResource(resourceKey, param, function (httpUrl) {
            if (httpUrl) {
                let restOption = thisInjectable.structureRestOption(httpUrl, option);
                if (option && (option.autoSpin || option.openSpin)) {
                    console.log('openSpin');
                }
                return thisInjectable.restService.get(restOption).subscribe(result => {
                    callback && callback.call(callback, result);
                    thisInjectable.callbackTreatOption(option);
                });
            } else {
                console.log('error');
            }
        });
    }

    put(resourceKey: string, param: any, payload: any, callback, option?: BaseOption) {
        let thisInjectable = this;
        this.resourceService.getResource(resourceKey, param, function (httpUrl) {
            if (httpUrl) {
                let restOption = thisInjectable.structureRestOption(httpUrl, payload, option);
                if (option && (option.autoSpin || option.openSpin)) {
                    console.log('openSpin');
                }
                return thisInjectable.restService.put(restOption).subscribe(result => {
                    callback && callback.call(callback, result);
                    thisInjectable.callbackTreatOption(option);
                });
            } else {
                console.log('error');
            }
        });
    }

    delete(resourceKey: string, param: any, payload: any, callback, option?: BaseOption) {
        let thisInjectable = this;
        this.resourceService.getResource(resourceKey, param, function (httpUrl) {
            if (httpUrl) {
                let restOption = thisInjectable.structureRestOption(httpUrl, payload, option);
                if (option && (option.autoSpin || option.openSpin)) {
                    console.log('openSpin');
                }
                return thisInjectable.restService.delete(restOption).subscribe(result => {
                    callback && callback.call(callback, result);
                    thisInjectable.callbackTreatOption(option);
                });
            } else {
                console.log('error');
            }
        });
    }
}
