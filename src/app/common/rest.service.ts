import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';

export interface RestOption {
    restUrl: string;
    httpHeader?: HttpHeaders;
    restBody?: any
    restHttpParams?: Object;
}

@Injectable()
export class RestService {

    constructor(private httpClient: HttpClient) {
    }

    public post(option: RestOption): Observable<any> {
        return Observable.defer(() => {
            return this.httpClient.post(option.restUrl, option.restBody, this.getHttpOptions(option));
        }).retryWhen((error) => {
            return this.refresh(error);
        });
    }

    public get(option: RestOption): Observable<any> {
        return Observable.defer(() => {
            return this.httpClient.get(option.restUrl, this.getHttpOptions(option));
        }).retryWhen((error) => {
            return this.refresh(error);
        });
    }

    public put(option: RestOption): Observable<any> {
        return Observable.defer(() => {
            return this.httpClient.put(option.restUrl, option.restBody, this.getHttpOptions(option));
        }).retryWhen((error) => {
            return this.refresh(error);
        });
    }

    public delete(option: RestOption): Observable<any> {
        return Observable.defer(() => {
            return this.httpClient.delete(option.restUrl, this.getHttpOptions(option));
        }).retryWhen((error) => {
            return this.refresh(error);
        });
    }

    private refresh(obs: Observable<any>): Observable<any> {
        return obs.switchMap((x: any) => {
            return Observable.throw(x);
        });
    }

    private getHttpOptions(option: RestOption): {} {
        let optionHttpHeader = option.httpHeader;
        if (!optionHttpHeader) {
            let headerHttp = new HttpHeaders();
            headerHttp = headerHttp.set('Content-Type', 'application/json;charset=UTF-8');
            headerHttp = headerHttp.set('Authorization', 'Basic YW5ndWxhcjphbmd1bGFy');
            optionHttpHeader = headerHttp;
        }
        let params = option.restHttpParams || [];
        let httpParams: HttpParams = new HttpParams();
        for (let paramKey in params) {
            if (params.hasOwnProperty(paramKey)) {
                let paramValue = params[paramKey];
                httpParams = httpParams.set(paramKey, paramValue);
            }
        }
        let httpOptions = {
            headers: optionHttpHeader,
            params: httpParams,
            body: option.restBody
        };
        return httpOptions;
    }

}