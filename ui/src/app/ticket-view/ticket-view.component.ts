import { Component, OnInit } from '@angular/core'
import { Router } from '@angular/router'
import { BookingService } from '../booking.service'

@Component({
  selector: 'app-ticket-view',
  templateUrl: './ticket-view.component.html',
  styleUrls: ['./ticket-view.component.scss'],
})
export class TicketViewComponent implements OnInit {
  booking!: any

  constructor(private bookingService: BookingService, private router: Router) {}

  ngOnInit(): void {
    this.bookingService.bookingStream.subscribe((booking) => {
      this.booking = booking
      console.log(this.booking.ticketResponse)
    })
  }
  navigateToBookingHistory() {
    this.bookingService.setNewBooking()
    this.router.navigate(['/booking-history'])
  }
}
