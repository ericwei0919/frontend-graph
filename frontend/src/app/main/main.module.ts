import {NgModule} from '@angular/core';
import {MainRoutingModule} from './main-routing.module';
import {MainOutletComponent} from './main.outlet.component';
import {ComingSoonComponent} from './components/comingSoon/coming.soon.component';

@NgModule({
    imports: [
        MainRoutingModule,
    ],
    declarations: [
        MainOutletComponent,
        ComingSoonComponent
    ],
})
export class MainModule {
}
