import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { Router } from '@angular/router'
import { BehaviorSubject } from 'rxjs'
import { BookingService } from './booking.service'

@Injectable({
  providedIn: 'root',
})
export class RouteService {
  api = 'http://localhost:8080/api/routes'

  getRoute(source: string, destination: string, travelDate: Date) {
    this.httpClient
      .get(
        `${this.api}/${source}/${destination}/${travelDate.getDate()}-${
          travelDate.getMonth() + 1
        }-${travelDate.getFullYear()}`,
      )
      .subscribe((route: any) => {
        this.bookingService.booking.route = route
        this.bookingService.booking.travelDate = route.travelDate
        this.bookingService.bookingStream.next(this.bookingService.booking)
      })
  }

  constructor(
    private httpClient: HttpClient,
    private bookingService: BookingService,
  ) {}
}
