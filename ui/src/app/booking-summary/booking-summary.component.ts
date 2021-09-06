import { Component, OnInit } from '@angular/core'
import { BookingService } from '../booking.service'
// import { RouteService } from '../route.service'

@Component({
  selector: 'app-booking-summary',
  templateUrl: './booking-summary.component.html',
  styleUrls: ['./booking-summary.component.scss'],
})
export class BookingSummaryComponent implements OnInit {
  booking!: any

  constructor(private bookingService: BookingService) {}

  ngOnInit(): void {
    this.bookingService.bookingStream.subscribe((booking) => {
      this.booking = booking
    })
  }

  handleChangeSeats(event: any): void {
    event.preventDefault()
    this.bookingService.changeSeats()
  }
}
