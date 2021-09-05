import { NgModule } from '@angular/core'
import { BrowserModule } from '@angular/platform-browser'

import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'
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
import { TokenInterceptorService } from './token-interceptor.service'
import { BookingHistoryComponent } from './booking-history/booking-history.component'
import { NavbarComponent } from './navbar/navbar.component'
import { AuthGuard } from './auth.guard';
import { MessageComponent } from './message/message.component'

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'booking' },
  { path: 'booking', component: BookingComponent },
  {
    path: 'booking-history',
    component: BookingHistoryComponent,
    canActivate: [AuthGuard],
  },
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
    BookingHistoryComponent,
    NavbarComponent,
    MessageComponent,
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
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
