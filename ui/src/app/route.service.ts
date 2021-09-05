import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { BehaviorSubject } from 'rxjs'

@Injectable({
  providedIn: 'root',
})
export class RouteService {
  api = 'http://localhost:8080/api/routes'

  routeId!: null | string
  selectedTripId!: null | string
  selectedSeats: Array<number> = []
  travellers: Array<any> = []
  travelDate!: null | string

  routeStream = new BehaviorSubject({})
  seatsStream = new BehaviorSubject({})

  getRoute(source: string, destination: string, travelDate: Date) {
    this.httpClient
      .get(
        `${this.api}/${source}/${destination}/${travelDate.getDate()}-${
          travelDate.getMonth() + 1
        }-${travelDate.getFullYear()}`,
      )
      .subscribe((route: any) => {
        this.routeId = route.id
        this.travelDate = route.travelDate
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

    let travellersReqPayload = []
    for (let traveller of travellers) {
      let t = {
        name: traveller.name,
        age: traveller.age,
        disabled: traveller.disabled,
        idProof: {
          type: traveller.idProof,
          number: traveller.idNumber,
        },
      }
      travellersReqPayload.push(t)
    }

    const ticketPayload = {
      travelDate: this.travelDate,
      routeId: this.routeId,
      tripId: this.selectedTripId,
      seatNumbers: this.selectedSeats,
      travellers: travellersReqPayload,
    }

    const api = 'http://localhost:8080/api/tickets'
    this.httpClient.post(api, ticketPayload).subscribe((response) => {
      console.log(response)
    })
  }

  constructor(private httpClient: HttpClient) {}
}
