import { Component, OnInit } from '@angular/core'
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms'
import { Observable } from 'rxjs'
import { map, startWith } from 'rxjs/operators'
import { RouteService } from '../route.service'

@Component({
  selector: 'app-search-form',
  templateUrl: './search-form.component.html',
  styleUrls: ['./search-form.component.scss'],
})
export class SearchFormComponent implements OnInit {
  searchFormGroup!: FormGroup

  options: string[] = ['chennai', 'bengaluru']
  filteredOptions!: Observable<string[]>

  ngOnInit() {
    this.searchFormGroup = this.fb.group({
      source: ['chennai', [Validators.required]],
      destination: ['bengaluru', [Validators.required]],
      travelDate: [new Date(2021, 9, 5), [Validators.required]],
    })
    this.filteredOptions = this.searchFormGroup.controls[
      'source'
    ].valueChanges.pipe(
      startWith(''),
      map((value) => this._filter(value)),
    )
    this.filteredOptions = this.searchFormGroup.controls[
      'destination'
    ].valueChanges.pipe(
      startWith(''),
      map((value) => this._filter(value)),
    )
  }

  handleSearch(event: Event): void {
    event.preventDefault()
    const { source, destination, travelDate } = this.searchFormGroup.value
    this.routeService.getRoute(source, destination, travelDate)
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase()

    return this.options.filter((option) =>
      option.toLowerCase().includes(filterValue),
    )
  }

  constructor(private fb: FormBuilder, private routeService: RouteService) {}
}
