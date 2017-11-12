import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {RouterModule} from "@angular/router";
import {FormsModule} from '@angular/forms';
import {TooltipModule} from "ngx-bootstrap/index";
import {ProcessingTasksComponent} from "./processing.tasks.component";
import {CompleteTasksComponent} from "./complete.tasks.component";
import {UpcomingTasksComponent} from "./upcoming.tasks.component";
import {ClaimTasksComponent} from "./claim.tasks.component";
import {CommonTaskComponent} from "./common/common.task.component";
import {ModalModule,TypeaheadModule} from "ngx-bootstrap/index";
import {CommonProcessTaskComponent} from "./common/classify/common.process.task.component";
import {TabsModule} from "ngx-bootstrap/index";

@NgModule({
  declarations: [
    ProcessingTasksComponent,
    CompleteTasksComponent,
    UpcomingTasksComponent,
    ClaimTasksComponent,
    CommonTaskComponent,
    CommonProcessTaskComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    RouterModule,
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    TypeaheadModule.forRoot(),
    TabsModule.forRoot()
  ],
  exports: [],
  entryComponents: [
    CommonProcessTaskComponent
  ]
})

export class TasksModule {
}
