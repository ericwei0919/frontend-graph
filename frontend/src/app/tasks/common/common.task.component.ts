import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'common-task',
  templateUrl: 'common.task.template.html'
})
export class CommonTaskComponent implements OnInit {

  @Input() todo:boolean;
  @Input() tasks:any;

  ngOnInit():void {}

  operating(task){
    console.log(task);
  }
}
