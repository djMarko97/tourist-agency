import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable, throwError} from 'rxjs';
import { AuthService } from 'src/app/services/authority/auth.service';
import {catchError} from 'rxjs/operators';


@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(catchError(error => {
      if (error.status === 401) {
        this.authService.logout();
        location.reload();
      }

      const errorMessage = (error.status && error.error.message) ? error.error.message : error.statusText;

      return throwError(errorMessage);
    }));
  }

}
