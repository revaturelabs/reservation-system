import { Component, OnInit } from '@angular/core'
import { BookingService } from '../booking.service'
import { RouteService } from '../route.service'

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.scss'],
})
export class BookingComponent implements OnInit {
  booking!: any

  constructor(private bookingService: BookingService) {}

  ngOnInit(): void {
    this.bookingService.setNewBooking()
    this.bookingService.bookingStream.subscribe((booking) => {
      this.booking = booking
    })
  }
}
