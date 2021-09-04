import { Component, OnInit } from '@angular/core'
import { FormArray, FormBuilder, FormGroup } from '@angular/forms'

@Component({
  selector: 'app-travellers',
  templateUrl: './travellers.component.html',
  styleUrls: ['./travellers.component.scss'],
})
export class TravellersComponent implements OnInit {
  travellersFormGroup: FormGroup = this.fb.group({
    travellers: this.fb.array([]),
  })

  get travellers() {
    return this.travellersFormGroup.controls['travellers'] as FormArray
  }

  handleSubmit(event: Event): void {
    console.log(this.travellersFormGroup.value)
  }

  constructor(private fb: FormBuilder) {}

  ngOnInit() {
    const traveller: FormGroup = this.fb.group({
      name: [''],
      age: [0],
      gender: [''],
      idProof: [''],
      idNumber: [''],
      disablity: ['false'],
    })
    this.travellers.push(traveller)
    this.travellers.push(traveller)
  }
}
