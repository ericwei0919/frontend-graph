import {Component} from '@angular/core';
import {SpinService} from '../../../common/spin/spin.service';

@Component({
    selector: 'coming-soon',
    template: `
        <div class="middle-box text-center loginscreen  animated fadeInDown">
            <button type="button" class="btn btn-info" (click)="openSpan()"> coming-soon</button>
        </div>
    `,
})
export class ComingSoonComponent {

    constructor(private spinService: SpinService) {

    }

    openSpan() {
        this.spinService.spin(true);
    }
}
