import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SessaoModalComponentComponent } from './sessao-modal-component.component';

describe('SessaoModalComponentComponent', () => {
  let component: SessaoModalComponentComponent;
  let fixture: ComponentFixture<SessaoModalComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SessaoModalComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SessaoModalComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
