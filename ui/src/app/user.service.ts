import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { tap } from 'rxjs/operators'
import { BehaviorSubject } from 'rxjs'

@Injectable({
  providedIn: 'root',
})
export class UserService {
  userStream = new BehaviorSubject({})

  constructor(private httpClient: HttpClient) {}

  save(userPayload: any) {
    let api = 'http://localhost:8080/api/users'
    return this.httpClient.post(api, userPayload)
  }

  isLoggedIn() {
    if (localStorage.getItem('auth-token')) return true
    else return false
  }

  login(credentials: any) {
    let api = 'http://localhost:8080/auth'
    return this.httpClient
      .post(api, {
        username: credentials.email,
        password: credentials.password,
      })
      .pipe(
        tap((response: any) => {
          const token = response.jwt
          localStorage.setItem('auth-token', token)
          this.userStream.next({ loggIn: true })
        }),
      )
  }
}
