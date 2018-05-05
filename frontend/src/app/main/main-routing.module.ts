import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {MainOutletComponent} from './main.outlet.component';
import {ComingSoonComponent} from './components/comingSoon/coming.soon.component';

const routes: Routes = [
    {
        path: '',
        component: MainOutletComponent,
        children: [
            {path: 'comingSoon', component: ComingSoonComponent},
            {path: '', redirectTo: 'comingSoon', pathMatch: 'full'},
            {path: '**', redirectTo: 'comingSoon'}
        ]
    }];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class MainRoutingModule {
}
