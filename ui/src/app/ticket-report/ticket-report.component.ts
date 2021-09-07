import { Component, OnInit } from '@angular/core'
import { MatDialog } from '@angular/material/dialog'
import { BookingService } from '../booking.service'
import { MessageComponent } from '../message/message.component'
import { TicketService } from '../ticket.service'

@Component({
  selector: 'app-ticket-report',
  templateUrl: './ticket-report.component.html',
  styleUrls: ['./ticket-report.component.scss'],
})
export class TicketReportComponent {
  displayedColumns: string[] = [
    'id',
    'source',
    'destination',
    'seats',
    'status',
  ]
  dataSource = []

  ngOnInit() {
    this.ticketService.getAllTickets().subscribe(
      (response: any) => {
        this.dataSource = response
      },
      (error) => {
        this.dialog.open(MessageComponent, {
          data: {
            message: ' You must be admin user',
          },
        })
      },
    )
  }
  constructor(private ticketService: TicketService, public dialog: MatDialog) {}
}
