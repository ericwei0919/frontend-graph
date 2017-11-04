import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpModule} from '@angular/http';
import {RouterModule} from '@angular/router';
import {AppComponent} from './app.component';
import {HttpService} from './common/http.service';
import {BaseComponent} from './base/base.component';
import {ROUTES} from "./app.routes";
import {AuthModule} from "./common/auth/auth.module";
import {LayoutsModule} from "./common/layouts/layouts.module";
import {HashLocationStrategy, LocationStrategy} from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    BaseComponent
  ],
  imports: [
    BrowserModule,HttpModule,
    RouterModule.forRoot(ROUTES),
    AuthModule,
    LayoutsModule
  ],
  providers: [
    HttpService,
    {provide: LocationStrategy, useClass: HashLocationStrategy}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
