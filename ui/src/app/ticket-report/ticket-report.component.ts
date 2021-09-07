// import { Component, OnInit } from '@angular/core';

// const TicketHistory=[
  // { 
  //   ticketId:"123456",
  //   userName:"raja",
  //   tripId:"123456",
  //   totalAmount:2000,
  //   ticketStatus:"confirmed"
  // },
  // { 
  //   ticketId:"123457",
  //   userName:"rahul",
  //   tripId:"13456",
  //   totalAmount:200,
  //   ticketStatus:"concelled"
  // },
  // { 
  //   ticketId:"123456",
  //   userName:"sheik",
  //   tripId:"156756",
  //   totalAmount:2000,
  //   ticketStatus:"waiting"
  // }
// ]

// @Component({
//   selector: 'app-ticket-report',
//   templateUrl: './ticket-report.component.html',
//   styleUrls: ['./ticket-report.component.scss']
// })
// export class TicketReportComponent implements OnInit {
  

  // displayedColumns: string[] = [
  //   'ticketId',
  //   'userName',
  //   'tripId',
  //   'totalAmount',
  //   'ticketStatus'
  // ]

//   datasource=TicketHistory;



//   constructor() { }

//   ngOnInit(): void {
//   }

// }


import {Component} from '@angular/core';

export interface TicketHistory {
  ticketId: string;
  userName: string;
  tripId: string;
  totalAmount: number;
  ticketStatus: string;
}

const data: TicketHistory[] = [
  { 
    ticketId:"123456",
    userName:"raja",
    tripId:"123456",
    totalAmount:2000,
    ticketStatus:"confirmed"
  },
  { 
    ticketId:"123457",
    userName:"rahul",
    tripId:"13456",
    totalAmount:200,
    ticketStatus:"cancelled"
  },
  { 
    ticketId:"123456",
    userName:"sheik",
    tripId:"156756",
    totalAmount:2000,
    ticketStatus:"waiting"
  }
];



/**
 * @title Basic use of `<table mat-table>`
 */
@Component({
  selector: 'app-ticket-report',
  templateUrl: './ticket-report.component.html',
  styleUrls: ['./ticket-report.component.scss']
})
export class TicketReportComponent {
  displayedColumns: string[] = [
    'ticketId',
    'userName',
    'tripId',
    'totalAmount',
    'ticketStatus'
  ]
  dataSource = data;
}

