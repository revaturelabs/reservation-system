import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { tap } from 'rxjs/operators'
import { BehaviorSubject } from 'rxjs'

import jwt_decode from 'jwt-decode'

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

  getToken() {
    if (localStorage.getItem('auth-token')) {
      return localStorage.getItem('auth-token')
    } else {
      return null
    }
  }

  isLoggedIn() {
    if (this.getToken()) return true
    else return false
  }

  getCurrentUser() {
    if (this.isLoggedIn()) {
      let decoded: any = jwt_decode(this.getToken() || '')
      return decoded.sub
    }
    return 'guest'
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
