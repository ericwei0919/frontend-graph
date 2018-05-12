import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {AuthOutletComponent} from './auth.outlet.component';
import {LoginComponent} from './components/login/login.component';

const routes: Routes = [
    {
        path: '',
        component: AuthOutletComponent,
        children: [
            {path: 'login', component: LoginComponent},
            {path: '', redirectTo: 'login', pathMatch: 'full'},
            {path: '**', redirectTo: 'login'}
        ]
    }];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class AuthRoutingModule {
}
