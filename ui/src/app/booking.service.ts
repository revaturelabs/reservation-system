import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { Router } from '@angular/router'
import { BehaviorSubject } from 'rxjs'

@Injectable({
  providedIn: 'root',
})
export class BookingService {
  api = 'http://localhost:8080/api/tickets'

  booking: any = {}

  bookingStream = new BehaviorSubject(this.booking)

  setNewBooking() {
    this.booking = {}
    this.bookingStream.next(this.booking)
  }

  getReservedSeats() {
    return this.booking.trip.reservedSeats
  }

  setTripWithSeats(tripId: string, seats: Array<number>): void {
    this.booking.tripId = tripId
    this.booking.trip = this.booking.route.tripList.find(
      (t: any) => t.id === tripId,
    )
    this.booking.seats = seats
    this.booking.changeSeats = false
    this.bookingStream.next(this.booking)
  }
  changeSeats() {
    this.booking.changeSeats = true
    this.bookingStream.next(this.booking)
  }
  setTravellers(travellers: Array<any>) {
    let travellersReqPayload = []
    for (let traveller of travellers) {
      let t = {
        name: traveller.name,
        age: traveller.age,
        gender: traveller.gender,
        disablity: traveller.disablity,
        idProof: {
          type: traveller.idProof,
          number: traveller.idNumber,
        },
      }
      travellersReqPayload.push(t)
    }

    this.booking.travellers = travellersReqPayload
    this.bookingStream.next(this.booking)
  }

  bookTicket() {
    const ticketPayload = {
      travelDate: this.booking.route.travelDate,
      routeId: this.booking.route.id,
      tripId: this.booking.tripId,
      seatNumbers: this.booking.seats,
      travellers: this.booking.travellers,
    }
    this.httpClient.post(this.api, ticketPayload).subscribe((response) => {
      this.booking = {}
      this.booking.ticketResponse = response
      this.bookingStream.next(this.booking)
    })
  }

  cancelTicket(ticketId: string) {
    this.httpClient.put(this.api + '/' + ticketId, {}).subscribe((response) => {
      this.bookingStream.next({
        cancelled: true,
      })
    })
  }

  getBookingHistory() {
    return this.httpClient.get(this.api)
  }

  constructor(private httpClient: HttpClient, private router: Router) {}
}
