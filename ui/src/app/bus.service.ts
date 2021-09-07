import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class BusService {
  bus: any = {}
  
  constructor(private httpClient: HttpClient) {}


  addBuses(data:Array<any>){
    console.log(data)
 
  let  api = 'http://localhost:8080/api/bus  '
    return this.httpClient.post(api,data)

  }



}
