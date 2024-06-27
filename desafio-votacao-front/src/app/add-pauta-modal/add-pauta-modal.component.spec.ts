import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPautaModalComponent } from './add-pauta-modal.component';

describe('AddPautaModalComponent', () => {
  let component: AddPautaModalComponent;
  let fixture: ComponentFixture<AddPautaModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddPautaModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddPautaModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
