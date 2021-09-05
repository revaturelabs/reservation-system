import { Component } from '@angular/core'
import { Router } from '@angular/router'
import { UserService } from './user.service'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  title = 'ui'

  isLoggedIn = false

  constructor(private userService: UserService, private router: Router) {}

  ngOnInit() {
    this.isLoggedIn = this.userService.isLoggedIn()
    this.userService.userStream.subscribe((data: any) => {
      if (data.loggIn) {
        this.isLoggedIn = true
      }
    })
  }

  handleLogout(event: Event) {
    event.preventDefault()
    localStorage.removeItem('auth-token')
    this.isLoggedIn = false
    this.router.navigate(['/'])
  }
}
