import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AcceptMailPageComponent } from './accept-mail-page.component';

describe('AcceptMailPageComponent', () => {
  let component: AcceptMailPageComponent;
  let fixture: ComponentFixture<AcceptMailPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AcceptMailPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AcceptMailPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
