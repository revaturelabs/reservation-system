import { Component, OnInit } from '@angular/core'

@Component({
  selector: 'app-route-trips',
  templateUrl: './route-trips.component.html',
  styleUrls: ['./route-trips.component.scss'],
})
export class RouteTripsComponent implements OnInit {
  constructor() {}

  route = {
    id: '1',
    source: 's1',
    destination: 'd1',
    distance: 100,
    tripList: [
      {
        id: 'CHNBNG1',
        depTime: Date.now(),
        arrivalTime: Date.now(),
        bus: {
          number: '1212',
          name: 'bus1',
          type: 'AC',
          seatType: 'CHAIR',
          seats: 40,
          contact: {
            name: 'c1',
            mobile: '1234567890',
          },
        },
        reservedSeats: [1, 2],
        price: 900,
      },
      {
        id: 'CHNBNG2',
        depTime: Date.now(),
        arrivalTime: Date.now(),
        bus: {
          number: '1213',
          name: 'bus1',
          type: 'AC',
          seatType: 'CHAIR',
          seats: 40,
          contact: {
            name: 'c1',
            mobile: '1234567890',
          },
        },
        reservedSeats: [1, 2],
        price: 900,
      },
    ],
  }

  ngOnInit(): void {}
}
