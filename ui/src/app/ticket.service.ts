import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { Router } from '@angular/router'

@Injectable({
  providedIn: 'root',
})
export class TicketService {
  getAllTickets() {
    return this.httpClient.get('http://localhost:8080/api/tickets/report')
  }

  constructor(private httpClient: HttpClient, private router: Router) {}
}
