import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {AppRoutingModule} from './app.routing.module';

@NgModule({
    imports: [
        AppRoutingModule,
        BrowserModule
    ],
    declarations: [
        AppComponent
    ],
    providers: [],
    exports: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
