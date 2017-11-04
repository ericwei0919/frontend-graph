import {Routes} from '@angular/router';
import {BlankLayoutComponent} from "./common/layouts/blankLayout.component";
import {LoginComponent} from "./common/auth/login.component";

export const ROUTES: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {
    path: '', component: BlankLayoutComponent,
    children: [
      {path: 'login', component: LoginComponent}
    ]
  }
];
