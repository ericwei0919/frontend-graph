import {Routes} from '@angular/router';
import {BlankLayoutComponent} from "./common/layouts/blankLayout.component";
import {BasicLayoutComponent} from "./common/layouts/basicLayout.component";
import {LoginComponent} from "./auth/login.component";
import {UserComponent} from "./setting/user.component";
import {GroupComponent} from "./setting/group.component";
import {UpcomingTasksComponent} from "./tasks/upcoming.tasks.component";
import {ProcessingTasksComponent} from "./tasks/processing.tasks.component";
import {CompleteTasksComponent} from "./tasks/complete.tasks.component";
import {ClaimTasksComponent} from "./tasks/claim.tasks.component";
import {TrendGraphComponent} from "./graph/trend.graph.component";
import {GroupUserComponent} from "./setting/group.user.component";

export const ROUTES: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {
    path: '', component: BlankLayoutComponent,
    children: [
      {path: 'login', component: LoginComponent}
    ]
  },
  {
    path: 'setting', component: BasicLayoutComponent,
    children: [
      {path: 'user', component: UserComponent},
      {path: 'group', component: GroupComponent},
      {path: 'grpUsr', component: GroupUserComponent}
    ]
  },
  {
    path: 'tasks', component: BasicLayoutComponent,
    children: [
      {path: 'complete', component: CompleteTasksComponent},
      {path: 'processing', component: ProcessingTasksComponent},
      {path: 'upcoming', component: UpcomingTasksComponent},
      {path: 'claim', component: ClaimTasksComponent}
    ]
  },
  {
    path: 'graph', component: BasicLayoutComponent,
    children: [
      {path: 'trend', component: TrendGraphComponent}
    ]
  }
];
