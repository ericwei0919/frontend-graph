import {ErrorHandler, Injectable} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';

@Injectable()
export class GeneralErrorHandler implements ErrorHandler {

    constructor() {
    }

    handleError(error: HttpErrorResponse) {
        console.log("GeneralErrorHandler");
        if (error.error instanceof ErrorEvent) {
            console.error('An error occurred:', error.error.message);
        } else {
            console.error(
                `Backend returned code ${error.status}, ` +
                `body was: ${error.error}`);
        }
    }
}
