import { Component, OnInit } from '@angular/core';
import {FormArray, FormControl, FormGroup,Validators, FormBuilder} from '@angular/forms';
import { BusService } from '../bus.service';

@Component({
  selector: 'app-bus-form',
  templateUrl: './bus-form.component.html',
  styleUrls: ['./bus-form.component.scss']
})
export class BusFormComponent implements OnInit {

buses: any 
 
  constructor(private fb: FormBuilder,private busService: BusService) { }

  // BusFormGroup: FormGroup = this.fb.group({
  //    buses: this.fb.array([]),
  // })

  // get buses(){
  //   return this.BusFormGroup.controls['buses'] as FormArray
  // }

  
  // handleSubmit(event: Event): void {
  //   this.busService.addBuses(this.BusFormGroup.value.buses)
  // }
  ngOnInit(){

    
  }
  
  BusFormGroup : FormGroup = this.fb.group({
    number: ['',[Validators.required]],
    name: ['',[Validators.required]],
    seatType: [''],
    seats:['42'],
    contacts: this.fb.array([])

  });
  get contacts(){
    return this.BusFormGroup.controls['contacts'] as FormArray
  }

  
  handleSubmit(event: any){
    // console.log(event)
    if(this.BusFormGroup.valid){
      let contactList = this.BusFormGroup.controls['contacts'].value.map( ( el:any )=>{
        return el.contacts; 
       } );

      this.buses = {
        number: this.BusFormGroup.controls['number'].value,
        name: this.BusFormGroup.controls['name'].value,
        seatType: this.BusFormGroup.controls['seatType'].value,
        seats: this.BusFormGroup.controls['seats'].value,
        contacts: null
      }
    }
    console.log(this.buses);
    this.busService.addBuses(this.buses).subscribe((response)=>{
      console.log(response);
    },(err)=>{
      console.log(err);
    });
    
  }


 

}