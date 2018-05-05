import {NgModule} from '@angular/core';
import {AuthOutletComponent} from './auth.outlet.component';
import {AuthRoutingModule} from './auth-routing.module';
import {LoginComponent} from './components/login/login.component';
import {AuthService} from './components/auth.service';

@NgModule({
    imports: [
        AuthRoutingModule,
    ],
    declarations: [
        AuthOutletComponent,
        LoginComponent
    ],
    providers: [
        AuthService
    ]
})
export class AuthModule {
}
