import {Injectable} from "@angular/core";
import {Headers, Http, RequestOptions, Response, URLSearchParams} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/Rx";

@Injectable()
export class HttpService {

  private options: RequestOptions;
  headers: any = new Headers();

  constructor(private http: Http) {}

  get(url: string, para?: any): Observable<any> {
    this.getHttpHeaders();
    return this.innerGet(url, para);
  }

  post(url: string, body: any): Observable<any> {
    this.getHttpHeaders();
    return Observable.defer(() => {
      return this.http.post(url, body, this.options)
        .map((res: Response) => {
          if (res.text() != '') {
            return res.json();
          }
        });
    }).retryWhen((error) => {
      return this.refresh(error);
    });
  }

  put(url: string, body: any): Observable<any> {
    this.getHttpHeaders();
    return Observable.defer(() => {
      return this.http.put(url, body, this.options)
        .map((res: Response) => {
          if (res.text() != '') {
            return res.json();
          }
        });
    }).retryWhen((error) => {
      return this.refresh(error);
    });
  }

  delete(url: string): Observable<any> {
    this.getHttpHeaders();
    return Observable.defer(() => {
      return this.http.delete(url, this.options)
        .map((res: Response) => {
          if (res.text() != '') {
            return res.json();
          }
        });
    }).retryWhen((error) => {
      return this.refresh(error);
    });
  }

  deleteBody(url: string, body: any): Observable<any> {
    this.getHttpHeaders();
    let options = new RequestOptions({headers: this.headers, body: body});
    return Observable.defer(() => {
      return this.http.delete(url, options)
        .map((res: Response) => {
          if (res.text() != '') {
            return res.json();
          }
        });
    }).retryWhen((error) => {
      return this.refresh(error);
    });
  }

  private getHttpHeaders() {
    this.getHeaders(localStorage.getItem('token'));
  }

  private getRefreshHeaders() {
    this.getHeaders(localStorage.getItem('refreshToken'));
  }

  private getHeaders(token: string) {
    this.headers.set('Content-Type', 'application/json;charset=UTF-8');
    this.headers.set('Authorization', 'Bearer ' + token);
    this.options = new RequestOptions({headers: this.headers});
    this.options.params = null;
  }

  private innerGet(url: string, para?: any): Observable<any> {
    let params: URLSearchParams = new URLSearchParams();
    for (let key in para) {
      if (para.hasOwnProperty(key)) {
        let value = para[key];
        params.set(key, value);
      }
    }
    this.options.params = params;
    return Observable.defer(() => {
      return this.http.get(url, this.options).map(res => res.json());
    }).retryWhen((error) => {
      return this.refresh(error);
    });
  }

  private refresh(obs: Observable<any>): Observable<any> {
    console.log(obs);
    return Observable.throw(obs);
  }

}
