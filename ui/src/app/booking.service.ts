import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core'

@Injectable({
  providedIn: 'root',
})
export class BookingService {
  constructor(private httpClient: HttpClient) {}

  getBookingHistory() {
    let api = 'http://localhost:8080/api/tickets'
    return this.httpClient.get(api)
  }
}
