import { NgModule } from '@angular/core'
import { BrowserModule } from '@angular/platform-browser'

import { FormsModule, ReactiveFormsModule } from '@angular/forms'

import { AppComponent } from './app.component'
import { SearchFormComponent } from './search-form/search-form.component'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'

import { MatModule } from './mat.module'
import { MatNativeDateModule } from '@angular/material/core';
import { RouteTripsComponent } from './route-trips/route-trips.component';
import { SeatsLayoutComponent } from './seats-layout/seats-layout.component';
import { TravellersComponent } from './travellers/travellers.component'

@NgModule({
  declarations: [AppComponent, SearchFormComponent, RouteTripsComponent, SeatsLayoutComponent, TravellersComponent],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MatNativeDateModule,
    MatModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
