import { Component, OnInit } from '@angular/core'
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms'
import { MatDialog } from '@angular/material/dialog'
import { Observable } from 'rxjs'
import { map, startWith } from 'rxjs/operators'
import { MessageComponent } from '../message/message.component'
import { RouteService } from '../route.service'

@Component({
  selector: 'app-search-form',
  templateUrl: './search-form.component.html',
  styleUrls: ['./search-form.component.scss'],
})
export class SearchFormComponent implements OnInit {
  searchFormGroup!: FormGroup
  minDate!: Date
  maxDate!: Date
  options: string[] = ['chennai', 'bengaluru']
  filteredOptions!: Observable<string[]>

  ngOnInit() {
    const today = new Date()
    const currentYear = today.getFullYear()
    const month = today.getMonth()
    const day = today.getDate()
    this.minDate = new Date(currentYear, month, day)
    this.maxDate = new Date(currentYear, month, day + 15)

    this.searchFormGroup = this.fb.group({
      source: ['chennai', [Validators.required]],
      destination: ['bengaluru', [Validators.required]],
      travelDate: [new Date(), [Validators.required]],
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
    if (source === destination) {
      this.dialog.open(MessageComponent, {
        data: {
          message: 'source & destination cannot be same',
        },
      })
      return
    }
    this.routeService.getRoute(source, destination, travelDate)
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase()

    return this.options.filter((option) =>
      option.toLowerCase().includes(filterValue),
    )
  }

  constructor(
    private fb: FormBuilder,
    private routeService: RouteService,
    public dialog: MatDialog,
  ) {}
}
