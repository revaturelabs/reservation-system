import { Component, OnInit } from '@angular/core'
import { BookingService } from '../booking.service'
import { RouteService } from '../route.service'

@Component({
  selector: 'app-booking-final',
  templateUrl: './booking-final.component.html',
  styleUrls: ['./booking-final.component.scss'],
})
export class BookingFinalComponent implements OnInit {
  constructor(private bookingService: BookingService) {}

  ngOnInit(): void {}

  payAndBook() {
    this.bookingService.bookTicket()
  }
}
