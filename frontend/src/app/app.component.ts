import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent extends BaseComponent{
  title = 'app';

  open() {
    this.httpService.get("user").subscribe(res => {
      this.title = res.content;
    });
  }
}
