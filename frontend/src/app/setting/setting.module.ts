import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {RouterModule} from "@angular/router";
import {FormsModule} from '@angular/forms';
import {TooltipModule,TypeaheadModule} from "ngx-bootstrap/index";
import {UserComponent} from "./user/user.component";
import {ModalModule} from "ngx-bootstrap/index";
import {GroupComponent} from "./group/group.component";
import {GroupUserComponent} from "./group/group.user.component";

@NgModule({
  declarations: [
    UserComponent,
    GroupComponent,
    GroupUserComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    RouterModule,
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    TypeaheadModule.forRoot()
  ],
  exports: [],
  entryComponents: [GroupUserComponent]
})

export class SettingModule {
}
