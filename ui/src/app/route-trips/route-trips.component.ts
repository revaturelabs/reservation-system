import { Component, OnInit } from '@angular/core'
import { RouteService } from '../route.service'
import * as moment from 'moment'
import { MatDialog } from '@angular/material/dialog'
import { SeatsLayoutComponent } from '../seats-layout/seats-layout.component'
import { BookingService } from '../booking.service'

@Component({
  selector: 'app-route-trips',
  templateUrl: './route-trips.component.html',
  styleUrls: ['./route-trips.component.scss'],
})
export class RouteTripsComponent implements OnInit {
  route!: any
  trips: Array<any> = []
  ngOnInit(): void {
    this.bookingService.bookingStream.subscribe((booking: any) => {
      this.trips = []
      this.route = booking.route
      if (this.route.tripList)
        for (let trip of this.route.tripList) {
          var dep = moment(trip.depTime, 'HH:mm')
          var arr = moment(trip.arrivalDateTime, 'dd/MM/yyyy HH:mm')
          var duration = moment.duration(arr.diff(dep))
          const tripRow = {
            id: trip.id,
            depTime: dep.hours() + ':' + dep.minutes(),
            arrTime: arr.hours() + ':' + arr.minutes(),
            travelDuration: duration,
            name: trip.bus.name,
            seats: 40 - trip.reservedSeats.length,
            price: trip.price,
            reservedSeats: trip.reservedSeats,
          }
          this.trips.push(tripRow)
        }
    })
  }

  viewSeats(tripId: string, reservedSeats: any) {
    console.log(tripId)
    console.log(reservedSeats)

    const dialogRef = this.dialog.open(SeatsLayoutComponent, {
      width: '60%',
      height: '80%',
      data: {
        reservedSeats: reservedSeats,
        selectedSeats: this.bookingService.booking.seats || [],
      },
    })
    dialogRef.afterClosed().subscribe((seats) => {
      if (seats) {
        this.bookingService.setTripWithSeats(tripId, seats)
      }
    })
  }

  constructor(
    public routeService: RouteService,
    public bookingService: BookingService,
    public dialog: MatDialog,
  ) {}
}
