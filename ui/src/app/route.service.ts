import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { BehaviorSubject } from 'rxjs'

import { tap } from 'rxjs/operators'

@Injectable({
  providedIn: 'root',
})
export class RouteService {
  api = 'http://localhost:8080/api/routes'

  routeId!: null | string
  selectedTripId!: null | string
  selectedSeats: Array<number> = []
  travellers: Array<any> = []

  routeStream = new BehaviorSubject({})
  seatsStream = new BehaviorSubject({})

  getRoute(source: string, destination: string, travelDate: Date) {
    this.httpClient
      .get(`${this.api}/${source}/${destination}/`)
      .subscribe((route: any) => {
        this.routeId = route.id
        this.routeStream.next(route)
      })
  }
  setTripWithSeats(tripId: string, seats: Array<number>): void {
    this.selectedTripId = tripId
    this.selectedSeats = seats
    console.log(this.selectedSeats)
    this.seatsStream.next({
      selectedSeats: this.selectedSeats,
    })
  }
  setTravellers(travellers: Array<any>) {
    this.travellers = travellers

    // book ticket
    const ticketPayload = {
      //travelDate: Date.now(),
      routeId: this.routeId,
      tripId: this.selectedTripId,
      seatNumbers: this.selectedSeats,
      travellers: this.travellers,
    }
    const api = 'http://localhost:8080/api/tickets'
    this.httpClient.post(api, ticketPayload).subscribe((response) => {
      console.log(response)
    })
  }

  constructor(private httpClient: HttpClient) {}
}
