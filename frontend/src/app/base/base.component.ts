import {Component} from "@angular/core";
import {HttpService} from "../common/http.service";
import {ActivatedRoute, Router} from "@angular/router";
import {BsModalService} from "ngx-bootstrap/index";

@Component({
  selector: 'app-base',
  template: '<div><div>'
})
export class BaseComponent {

  constructor(public httpService: HttpService,
              public router: Router,
              public activeRoute: ActivatedRoute,
			        public modalService: BsModalService) {
  }

}
