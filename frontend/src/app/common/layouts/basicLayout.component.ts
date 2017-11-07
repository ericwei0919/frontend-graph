import { Component } from '@angular/core';
import {Router} from '@angular/router';

declare var jQuery:any;

@Component({
  selector: 'basic',
  templateUrl: 'basicLayout.template.html',
  host: {
    '(window:resize)': 'onResize()'
  }
})
export class BasicLayoutComponent {
  constructor(public router:Router) {
  }

  public ngOnInit():any {
    if (jQuery(document).width() < 769) {
      jQuery('body').addClass('body-small')
    } else {
      jQuery('body').removeClass('body-small')
    }
  }

  public onResize() {
    if (jQuery(document).width() < 769) {
      jQuery('body').addClass('body-small')
    } else {
      jQuery('body').removeClass('body-small')
    }
  }

  gotoActiveRoute(url) {
    this.router.navigate([url]);
  }

  getCSSClasses(routename):string {
    let cssClasses = '';
    if (this.router.url.indexOf(routename) > -1) {
      cssClasses = 'warning-element';
    } else {
      cssClasses = 'success-element';
    }
    return cssClasses;
  }
}
