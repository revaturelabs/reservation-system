import { NgModule } from '@angular/core'
import { BrowserModule } from '@angular/platform-browser'

import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { HttpClientModule } from '@angular/common/http'
import { RouterModule, Routes } from '@angular/router'

import { AppComponent } from './app.component'
import { SearchFormComponent } from './search-form/search-form.component'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'

import { MatModule } from './mat.module'

import { MatNativeDateModule } from '@angular/material/core'
import { RouteTripsComponent } from './route-trips/route-trips.component'
import { SeatsLayoutComponent } from './seats-layout/seats-layout.component'
import { LoginFormComponent } from './login-form/login-form.component'
import { RegisterFormComponent } from './register-form/register-form.component'
import { BookingComponent } from './booking/booking.component'
import { TravellersFormComponent } from './travellers-form/travellers-form.component'

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'booking' },
  { path: 'booking', component: BookingComponent },
  { path: 'login', component: LoginFormComponent },
  { path: 'register', component: RegisterFormComponent },
]

@NgModule({
  declarations: [
    AppComponent,
    SearchFormComponent,
    RouteTripsComponent,
    SeatsLayoutComponent,
    TravellersFormComponent,
    BookingComponent,
    LoginFormComponent,
    RegisterFormComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes),
    MatNativeDateModule,
    MatModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
