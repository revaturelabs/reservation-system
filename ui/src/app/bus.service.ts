import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class BusService {

  addBuses(buses:Array<any>){
  let  api = 'http://localhost:8080/api/bus  '
    console.log(buses)
    return this.httpClient.post(api,buses)

  }


   constructor(private httpClient: HttpClient, private router: Router) {}

}
