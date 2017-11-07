import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {RouterModule} from "@angular/router";
import {FormsModule} from '@angular/forms';
import {TooltipModule} from "ngx-bootstrap/index";
import {ProcessingTasksComponent} from "./processing.tasks.component";
import {CompleteTasksComponent} from "./complete.tasks.component";
import {UpcomingTasksComponent} from "./upcoming.tasks.component";
import {ClaimTasksComponent} from "./claim.tasks.component";

@NgModule({
  declarations: [
    ProcessingTasksComponent,
    CompleteTasksComponent,
    UpcomingTasksComponent,
    ClaimTasksComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    RouterModule,
    TooltipModule.forRoot()
  ],
  exports: [],
})

export class TasksModule {
}
