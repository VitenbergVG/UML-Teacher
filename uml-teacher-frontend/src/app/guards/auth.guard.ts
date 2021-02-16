import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable } from "rxjs";
import { AuthorizationService } from "../services/authorization.service";

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private authorizationService: AuthorizationService,
        private router: Router) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        if (!this.authorizationService.currentUser) {
            this.router.navigate(['/login']);
            return false;
        }
        return true;
    }


}
