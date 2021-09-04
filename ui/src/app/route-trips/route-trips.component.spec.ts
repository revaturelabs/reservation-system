import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RouteTripsComponent } from './route-trips.component';

describe('RouteTripsComponent', () => {
  let component: RouteTripsComponent;
  let fixture: ComponentFixture<RouteTripsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RouteTripsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RouteTripsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
