import {BrowserModule} from '@angular/platform-browser';
import {ErrorHandler, NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';
import {RestService} from './common/rest.service';
import {HashLocationStrategy, LocationStrategy} from '@angular/common';
import {GeneralErrorHandler} from './common/general.error.handler';
import {ResourceService} from './common/resource.service';
import {BaseService} from './common/base.service';

@NgModule({
    imports: [
        AppRoutingModule,
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule
    ],
    declarations: [
        AppComponent
    ],
    providers: [
        RestService,
        ResourceService,
        BaseService,
        {provide: LocationStrategy, useClass: HashLocationStrategy},
        {provide: ErrorHandler, useClass: GeneralErrorHandler}
    ],
    exports: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
