import { Component, Inject, OnInit } from '@angular/core'
import {
  MatDialog,
  MatDialogRef,
  MAT_DIALOG_DATA,
} from '@angular/material/dialog'
@Component({
  selector: 'app-seats-layout',
  templateUrl: './seats-layout.component.html',
  styleUrls: ['./seats-layout.component.scss'],
})
export class SeatsLayoutComponent implements OnInit {
  selectedSeats: Array<Number> = []
  reservedSeats: Array<Number> = []

  handleSeatSelect(seatNumber: number): void {
    if (this.reservedSeats.indexOf(seatNumber) !== -1) return

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

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {}

  ngOnInit(): void {
    this.reservedSeats = this.data.reservedSeats
    this.selectedSeats = this.data.selectedSeats
  }
}
