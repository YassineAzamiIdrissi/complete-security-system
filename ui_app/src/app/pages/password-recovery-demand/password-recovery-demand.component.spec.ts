import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PasswordRecoveryDemandComponent } from './password-recovery-demand.component';

describe('PasswordRecoveryDemandComponent', () => {
  let component: PasswordRecoveryDemandComponent;
  let fixture: ComponentFixture<PasswordRecoveryDemandComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PasswordRecoveryDemandComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PasswordRecoveryDemandComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
