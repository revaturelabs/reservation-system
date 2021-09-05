import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core'

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private httpClient: HttpClient) {}

  save(userPayload: any) {
    let api = 'http://localhost:8080/api/users'
    return this.httpClient.post(api, userPayload)
  }
}
