import { Component, OnInit } from '@angular/core'

@Component({
  selector: 'app-seats-layout',
  templateUrl: './seats-layout.component.html',
  styleUrls: ['./seats-layout.component.scss'],
})
export class SeatsLayoutComponent implements OnInit {
  selectedSeats: Array<Number> = []
  reservedSeats: Array<Number> = [12, 13]

  handleSeatSelect(seatNumber: number): void {
    let idx = this.selectedSeats.indexOf(seatNumber)
    if (idx == -1) this.selectedSeats.push(seatNumber)
    else {
      this.selectedSeats.splice(idx, 1)
    }
  }

  isSelected(seatNumber: number): boolean {
    return this.selectedSeats.indexOf(seatNumber) != -1
  }
  isReservedSeat(seatNumber: number): boolean {
    return this.reservedSeats.indexOf(seatNumber) != -1
  }

  getColor(seatNumber: number) {
    if (this.isReservedSeat(seatNumber)) {
      return 'red'
    } else if (this.isSelected(seatNumber)) {
      return 'blue'
    } else return ''
  }

  constructor() {}

  ngOnInit(): void {}
}
