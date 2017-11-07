import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {RouterModule} from "@angular/router";

import {BsDropdownModule} from 'ngx-bootstrap';
import {BlankLayoutComponent} from "./blankLayout.component";
import {BasicLayoutComponent} from "./basicLayout.component";

@NgModule({
  declarations: [
    BlankLayoutComponent,
    BasicLayoutComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    BsDropdownModule.forRoot()
  ],
  exports: [
    BlankLayoutComponent,
    BasicLayoutComponent
  ],
})

export class LayoutsModule {
}
