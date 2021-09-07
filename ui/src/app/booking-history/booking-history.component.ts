import { Component, OnInit } from '@angular/core'
import { BookingService } from '../booking.service'

@Component({
  selector: 'app-booking-history',
  templateUrl: './booking-history.component.html',
  styleUrls: ['./booking-history.component.scss'],
})
export class BookingHistoryComponent implements OnInit {
  history = []
  displayedColumns: string[] = [
    'source',
    'destination',
    'date',
    'seats',
    'status',
    'cancel',
  ]
  constructor(private bookingService: BookingService) {}

  ngOnInit(): void {
    this.bookingService.getBookingHistory().subscribe((response: any) => {
      this.history = response
    })

    this.bookingService.bookingStream.subscribe((data: any) => {
      if (data.cancelled) {
        this.bookingService.getBookingHistory().subscribe((response: any) => {
          this.history = response
        })
      }
    })
  }

  cancelTicket(ticketId: string): void {
    this.bookingService.cancelTicket(ticketId)
  }
}
