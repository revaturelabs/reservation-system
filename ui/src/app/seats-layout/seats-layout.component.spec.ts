import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeatsLayoutComponent } from './seats-layout.component';

describe('SeatsLayoutComponent', () => {
  let component: SeatsLayoutComponent;
  let fixture: ComponentFixture<SeatsLayoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SeatsLayoutComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SeatsLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
