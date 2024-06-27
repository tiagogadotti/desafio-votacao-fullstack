import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PautaModalComponent } from './pauta-modal.component';

describe('AddPautaModalComponent', () => {
  let component: PautaModalComponent;
  let fixture: ComponentFixture<PautaModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PautaModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PautaModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
