import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusFormComponent } from './bus-form.component';

describe('BusFormComponent', () => {
  let component: BusFormComponent;
  let fixture: ComponentFixture<BusFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BusFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BusFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
