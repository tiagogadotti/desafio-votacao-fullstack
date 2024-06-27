import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IniciarSessaoDialogComponent } from './iniciar-sessao-dialog.component';

describe('IniciarSessaoDialogComponent', () => {
  let component: IniciarSessaoDialogComponent;
  let fixture: ComponentFixture<IniciarSessaoDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IniciarSessaoDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IniciarSessaoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
