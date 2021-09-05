import { Injectable } from '@angular/core'
import { MatDialog } from '@angular/material/dialog'
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router'
import { Observable } from 'rxjs'
import { MessageComponent } from './message/message.component'
import { UserService } from './user.service'

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(
    private userService: UserService,
    private router: Router,
    public dialog: MatDialog,
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot,
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    if (this.userService.isLoggedIn()) return true
    else {
      this.dialog.open(MessageComponent, {})
      this.router.navigate(['/login'])
      return false
    }
  }
}
