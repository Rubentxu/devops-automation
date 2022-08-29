import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateChild, CanLoad, Route, Router, RouterStateSnapshot, UrlSegment } from '@angular/router';
import { Observable, of, switchMap } from 'rxjs';
import {AuthService} from "../../services/auth/auth.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate, CanActivateChild, CanLoad {

  /**
   * Constructor
   */
  constructor(
    private authService: AuthService,
    private router: Router
  ) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    const redirectUrl = state.url === '/logout' ? '/' : state.url;
    return this.check(redirectUrl);
  }
  canActivateChild(childRoute: ActivatedRouteSnapshot,state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    const redirectUrl = state.url === '/logout' ? '/' : state.url;
    return this.check(redirectUrl);
  }
  canLoad(route: Route, segments: UrlSegment[]): Observable<boolean> | Promise<boolean> | boolean {
    return this.check('/');
  }

  check(redirectURL: string): Observable<boolean> {
    return this.authService.check()
      .pipe(
        switchMap((authenticated) => {
          if (!authenticated) {
            this.router.navigate(['login'], {queryParams: {redirectURL}});
            return of(false);
          }
          return of(true);
        })
      )
  }
}
