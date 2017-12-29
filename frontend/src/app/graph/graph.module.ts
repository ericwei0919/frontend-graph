import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {RouterModule} from "@angular/router";
import {FormsModule} from '@angular/forms';
import {TooltipModule} from "ngx-bootstrap/index";
import {TrendGraphComponent} from "./trend.graph.component";
import {AngularEchartsModule} from 'ngx-echarts';

@NgModule({
  declarations: [
    TrendGraphComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    RouterModule,
    TooltipModule.forRoot(),
    AngularEchartsModule
  ],
  exports: [],
})

export class GraphModule {
}
