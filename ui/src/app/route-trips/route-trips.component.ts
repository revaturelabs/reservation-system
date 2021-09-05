import { Component, OnInit } from '@angular/core'
import { RouteService } from '../route.service'
import * as moment from 'moment'
import { MatDialog } from '@angular/material/dialog'
import { SeatsLayoutComponent } from '../seats-layout/seats-layout.component'

@Component({
  selector: 'app-route-trips',
  templateUrl: './route-trips.component.html',
  styleUrls: ['./route-trips.component.scss'],
})
export class RouteTripsComponent implements OnInit {
  route = null
  trips: Array<any> = []
  ngOnInit(): void {
    this.routeService.routeStream.subscribe((route: any) => {
      this.route = route
      if (route.tripList)
        for (let trip of route.tripList) {
          var dep = moment(trip.depTime, 'dd/MM/yyyy HH:mm')
          var arr = moment(trip.arrivalTime, 'dd/MM/yyyy HH:mm')
          var duration = moment.duration(arr.diff(dep))

          const tripRow = {
            id: trip.id,
            depTime: dep.hours() + ':' + dep.minutes(),
            arrTime: arr.hours() + ':' + arr.minutes(),
            travelDuration: duration,
            name: trip.bus.name,
            seats: 40 - trip.reservedSeats,
            price: trip.price,
            reservedSeats: [1],
          }
          this.trips.push(tripRow)
        }
    })
  }

  viewSeats(tripId: string) {
    const dialogRef = this.dialog.open(SeatsLayoutComponent, {
      width: '70%',
      height: '80%',
      data: {
        reservedSeats: [1],
        selectedSeats: this.routeService.selectedSeats,
      },
    })
    dialogRef.afterClosed().subscribe((seats) => {
      if (seats) {
        this.routeService.setTripWithSeats(tripId, seats)
      }
    })
  }

  constructor(public routeService: RouteService, public dialog: MatDialog) {}
}
