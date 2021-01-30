import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {UsersService} from "./users-service.service";

@Injectable()
export class AuthGuardService implements CanActivate {

  constructor(private userService: UsersService, private router: Router) {

  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if(this.userService.currentUser == null) {
      this.router.navigate(['login']);
    }
    return true;
  }

}
