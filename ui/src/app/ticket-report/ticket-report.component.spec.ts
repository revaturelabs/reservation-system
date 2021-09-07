import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketReportComponent } from './ticket-report.component';

describe('TicketReportComponent', () => {
  let component: TicketReportComponent;
  let fixture: ComponentFixture<TicketReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TicketReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TicketReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
