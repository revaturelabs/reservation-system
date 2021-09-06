import { Component, OnInit } from '@angular/core'
import { FormArray, FormBuilder, FormGroup } from '@angular/forms'
import { BookingService } from '../booking.service'
import { RouteService } from '../route.service'

@Component({
  selector: 'app-travellers-form',
  templateUrl: './travellers-form.component.html',
  styleUrls: ['./travellers-form.component.scss'],
})
export class TravellersFormComponent implements OnInit {
  selectedSeats = []

  travellersFormGroup: FormGroup = this.fb.group({
    travellers: this.fb.array([]),
  })

  get travellers() {
    return this.travellersFormGroup.controls['travellers'] as FormArray
  }

  handleSubmit(event: Event): void {
    this.bookingService.setTravellers(this.travellersFormGroup.value.travellers)
  }

  constructor(
    private fb: FormBuilder,
    private bookingService: BookingService,
  ) {}

  ngOnInit() {
    let travellers = this.bookingService.booking.seats || []
    for (let seat of travellers) {
      const traveller: FormGroup = this.fb.group({
        name: [''],
        age: [0],
        gender: [''],
        idProof: [''],
        idNumber: [''],
        disablity: ['false'],
      })
      this.travellers.push(traveller)
    }
  }
}
