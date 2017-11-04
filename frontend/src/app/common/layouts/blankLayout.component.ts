import {AfterViewInit, Component, OnDestroy} from '@angular/core';

declare var jQuery: any;

@Component({
  selector: 'blank',
  templateUrl: 'blankLayout.template.html'
})
export class BlankLayoutComponent implements AfterViewInit, OnDestroy {

  ngAfterViewInit(): void {
    jQuery('body').addClass('gray-bg');
  }

  ngOnDestroy(): void {
    jQuery('body').removeClass('gray-bg');
  }

}
