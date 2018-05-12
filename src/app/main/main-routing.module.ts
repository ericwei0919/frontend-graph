import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {MainOutletComponent} from './main.outlet.component';
import {ComingSoonComponent} from './components/comingSoon/coming.soon.component';
import {HomepageComponent} from './framework/homepage.component';

const routes: Routes = [
    {
        path: '',
        component: MainOutletComponent,
        children: [
            {path: 'comingSoon', component: ComingSoonComponent},
            {path: 'homepage', component: HomepageComponent},
            {path: '', redirectTo: 'homepage', pathMatch: 'full'},
            {path: '**', redirectTo: 'homepage'}
        ]
    }];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class MainRoutingModule {
}
