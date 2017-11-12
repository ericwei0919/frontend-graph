import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {BaseComponent} from "../../base/base.component";
import {BsModalRef} from 'ngx-bootstrap/modal/bs-modal-ref.service';
import {CommonProcessTaskComponent} from "./classify/common.process.task.component";

@Component({
  selector: 'common-task',
  templateUrl: 'common.task.template.html'
})
export class CommonTaskComponent extends BaseComponent implements OnInit {

  @Input() todo:boolean;
  @Input() tasks:any;

  bsModalRef:BsModalRef;
  ngOnInit():void {}

  operating(task){
    this.bsModalRef = this.modalService.show(CommonProcessTaskComponent);
    this.bsModalRef.content.task = task;
    this.bsModalRef.content.bsModalRef = this.bsModalRef;
  }
}
