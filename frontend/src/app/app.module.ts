import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpModule} from '@angular/http';
import {RouterModule} from '@angular/router';
import {AppComponent} from './app.component';
import {HttpService} from './common/http.service';
import {BaseComponent} from './base/base.component';
import {ROUTES} from "./app.routes";
import {LayoutsModule} from "./common/layouts/layouts.module";
import {HashLocationStrategy, LocationStrategy} from '@angular/common';
import {AuthModule} from "./auth/auth.module";
import {SettingModule} from "./setting/setting.module";
import {TasksModule} from "./tasks/tasks.module";
import {GraphModule} from "./graph/graph.module";
import {ModalModule} from "ngx-bootstrap/index";

@NgModule({
  declarations: [
    AppComponent,
    BaseComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    RouterModule.forRoot(ROUTES),
    ModalModule.forRoot(),
    AuthModule,
    LayoutsModule,
    SettingModule,
    TasksModule,
    GraphModule
  ],
  providers: [
    HttpService,
    {provide: LocationStrategy, useClass: HashLocationStrategy}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
