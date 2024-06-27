import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VotacaoModalComponent } from './votacao-modal.component';

describe('VotacaoModalComponent', () => {
  let component: VotacaoModalComponent;
  let fixture: ComponentFixture<VotacaoModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VotacaoModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VotacaoModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
