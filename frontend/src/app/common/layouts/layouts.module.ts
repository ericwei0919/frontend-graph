import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {RouterModule} from "@angular/router";

import {BsDropdownModule} from 'ngx-bootstrap';
import {BlankLayoutComponent} from "./blankLayout.component";

@NgModule({
  declarations: [
    BlankLayoutComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    BsDropdownModule.forRoot()
  ],
  exports: [
    BlankLayoutComponent
  ],
})

export class LayoutsModule {
}
