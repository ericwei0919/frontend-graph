import {Injectable} from '@angular/core';

@Injectable()
export class ResourceService {

    resources = {
        user: 'user/:param/:action',
    };

    _getUrl(resourceKey: string, parameter: any): string {
        if (resourceKey && this.resources[resourceKey]) {
            let proxy = '';
            let parts = this.resources[resourceKey].split('/');
            let finalParts = [];
            parts.forEach(function (part) {
                if (part.indexOf(':') > -1 || part.indexOf(location.host) > -1) {
                    let partKey = part.substr(part.indexOf(':') + 1, part.length);
                    if (parameter instanceof Object && parameter[partKey]) {
                        finalParts.push(parameter[partKey]);
                    }
                } else {
                    finalParts.push(part);
                }
            });
            return proxy + finalParts.join('/');
        } else {
            return undefined;
        }
    };

    getResource(resourceKey: string, parameter: any, callback: any) {
        let httpUrl = this._getUrl(resourceKey, parameter);
        callback && callback.call(callback, httpUrl);
    };

}
