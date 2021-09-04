import { Component, OnInit } from '@angular/core'
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms'
import { Observable } from 'rxjs'
import { map, startWith } from 'rxjs/operators'

@Component({
  selector: 'app-search-form',
  templateUrl: './search-form.component.html',
  styleUrls: ['./search-form.component.scss'],
})
export class SearchFormComponent implements OnInit {
  searchFormGroup!: FormGroup

  options: string[] = ['chennai', 'benagluru', 'hyderabad']
  filteredOptions!: Observable<string[]>

  ngOnInit() {
    this.searchFormGroup = this.fb.group({
      source: ['', [Validators.required]],
      destination: ['', [Validators.required]],
      travelDate: ['', [Validators.required]],
    })
    // this.filteredOptions = this.myControl.valueChanges.pipe(
    //   startWith(''),
    //   map((value) => this._filter(value)),
    // )
  }

  handleSearch(event: Event): void {
    event.preventDefault()
    console.log(this.searchFormGroup.value)
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase()

    return this.options.filter((option) =>
      option.toLowerCase().includes(filterValue),
    )
  }

  constructor(private fb: FormBuilder) {}
}
