import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {RouterModule} from "@angular/router";
import {FormsModule} from '@angular/forms';
import {TooltipModule} from "ngx-bootstrap/index";
import {UserComponent} from "./user.component";
import {GroupComponent} from "./group.component";

@NgModule({
  declarations: [
    UserComponent,
    GroupComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    RouterModule,
    TooltipModule.forRoot()
  ],
  exports: [],
})

export class SettingModule {
}
