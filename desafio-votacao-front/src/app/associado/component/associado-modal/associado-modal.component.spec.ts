import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssociadoModalComponent } from './associado-modal.component';

describe('NovoAssociadoComponent', () => {
  let component: AssociadoModalComponent;
  let fixture: ComponentFixture<AssociadoModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AssociadoModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssociadoModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
