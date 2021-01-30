import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {catchError, tap} from "rxjs/operators";
import {Injectable} from "@angular/core";
import {Router} from "@angular/router";
import {UsersService} from "./users-service.service";

@Injectable()
export class AppHttpInterceptor implements HttpInterceptor {

  constructor(private router: Router, private userService: UsersService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
   if(localStorage.getItem("id_token") !== null) {
     req.headers.append("Authorization", "Bearer " +localStorage.getItem("id_token"));
   }

   return next.handle(req)
     .pipe(
       tap(evt => {}),
       catchError((err, caught) => {
         if(err instanceof HttpErrorResponse) {
           if(err.status !== 401) {
              return caught;
           }
           localStorage.removeItem('id_token');
           this.userService.currentUser = null;
           this.router.navigate(['login']);
         }

         return caught;
       })
     )
  }

}
