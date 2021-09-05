import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TravellersFormComponent } from './travellers-form.component';

describe('TravellersFormComponent', () => {
  let component: TravellersFormComponent;
  let fixture: ComponentFixture<TravellersFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TravellersFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TravellersFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
