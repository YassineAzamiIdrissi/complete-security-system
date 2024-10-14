import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SetNewPassComponent } from './set-new-pass.component';

describe('SetNewPassComponent', () => {
  let component: SetNewPassComponent;
  let fixture: ComponentFixture<SetNewPassComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SetNewPassComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SetNewPassComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
