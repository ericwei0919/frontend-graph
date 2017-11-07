import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {RouterModule} from "@angular/router";

import {FormsModule} from '@angular/forms';
import {LoginComponent} from "./login.component";
import {TooltipModule} from "ngx-bootstrap/index";

@NgModule({
  declarations: [LoginComponent],
  imports: [
    FormsModule,
    BrowserModule,
    RouterModule,
    TooltipModule.forRoot()
  ],
  exports: [],
})

export class AuthModule {
}
