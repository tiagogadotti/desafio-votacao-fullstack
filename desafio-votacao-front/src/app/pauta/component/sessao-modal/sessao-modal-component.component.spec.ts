import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SessaoModal } from './sessao-modal-component.component';

describe('SessaoModalComponentComponent', () => {
  let component: SessaoModal;
  let fixture: ComponentFixture<SessaoModal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SessaoModal]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SessaoModal);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
