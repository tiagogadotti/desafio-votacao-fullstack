import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NovoAssociadoComponent } from './novo-associado.component';

describe('NovoAssociadoComponent', () => {
  let component: NovoAssociadoComponent;
  let fixture: ComponentFixture<NovoAssociadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NovoAssociadoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NovoAssociadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
