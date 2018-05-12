import {NgModule} from '@angular/core';
import {MainRoutingModule} from './main-routing.module';
import {MainOutletComponent} from './main.outlet.component';
import {ComingSoonComponent} from './components/comingSoon/coming.soon.component';
import {HomepageComponent} from './framework/homepage.component';
import {BsDatepickerModule} from 'ngx-bootstrap';
import {BodyComponent} from './framework/body/body.component';
import {HeaderComponent} from './framework/header/header.component';
import {FooterComponent} from './framework/footer/footer.component';

@NgModule({
    imports: [
        MainRoutingModule,
        BsDatepickerModule.forRoot()
    ],
    declarations: [
        MainOutletComponent,
        ComingSoonComponent,
        HomepageComponent,
        BodyComponent,
        HeaderComponent,
        FooterComponent
    ],
})
export class MainModule {
}
