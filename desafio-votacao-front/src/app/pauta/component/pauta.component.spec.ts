import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialog } from "@angular/material/dialog";
import { MatSnackBar } from "@angular/material/snack-bar";
import { of, throwError } from 'rxjs';
import { PautaComponent } from './pauta.component';
import { PautaService } from '../services/pauta.service';
import { SessaoService } from '../services/sessao.service';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import {HttpClient} from "@angular/common/http";
import {PautaInfo} from "../PautaInfo";
import {SessaoDTO} from "../SessaoDTO";
import {PautaDTO} from "../PautaDTO";

describe('PautaComponent', () => {
  let component: PautaComponent;
  let fixture: ComponentFixture<PautaComponent>;
  let pautaService: jasmine.SpyObj<PautaService>;
  let sessaoService: jasmine.SpyObj<SessaoService>;
  let matDialog: jasmine.SpyObj<MatDialog>;
  let matSnackBar: jasmine.SpyObj<MatSnackBar>;

  const mockPautaInfo: PautaInfo = {
    pautaId: 1,
    pautaTitulo: 'Pauta de Teste',
    pautaDescricao: 'Descrição da Pauta de Teste',
    sessaoId: 1,
    sessaoInicio: new Date('2022-01-01T00:00:00'),
    sessaoDuracao: 60,
    sessaoTotalVotosSim: 10,
    sessaoTotalVotosNao: 5
  };

  const mockPautasInfo: PautaInfo[] = [mockPautaInfo];

  const mockPauta: PautaDTO = {
    id: 1,
    titulo: "Titulo",
    descricao: "Descrição"
  }

  const mockSessaoDTO: SessaoDTO = {
    id: 1,
    pautaId: 1,
    inicio: new Date('2022-01-01T00:00:00'),
    duracao: 60
  };

  beforeEach(async () => {
    const pautaServiceSpy = jasmine.createSpyObj('PautaService', ['getPautas', 'save']);
    const sessaoServiceSpy = jasmine.createSpyObj('SessaoService', ['save']);
    const matDialogSpy = jasmine.createSpyObj('MatDialog', ['open']);
    const matSnackBarSpy = jasmine.createSpyObj('MatSnackBar', ['open']);

    await TestBed.configureTestingModule({
      declarations: [PautaComponent],
      imports: [
        MatCardModule,
        MatButtonModule,
        MatIconModule,
        HttpClient
      ],
      providers: [
        { provide: PautaService, useValue: pautaServiceSpy },
        { provide: SessaoService, useValue: sessaoServiceSpy },
        { provide: MatDialog, useValue: matDialogSpy },
        { provide: MatSnackBar, useValue: matSnackBarSpy }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(PautaComponent);
    component = fixture.componentInstance;
    pautaService = TestBed.inject(PautaService) as jasmine.SpyObj<PautaService>;
    sessaoService = TestBed.inject(SessaoService) as jasmine.SpyObj<SessaoService>;
    matDialog = TestBed.inject(MatDialog) as jasmine.SpyObj<MatDialog>;
    matSnackBar = TestBed.inject(MatSnackBar) as jasmine.SpyObj<MatSnackBar>;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load pautas on init', () => {
    pautaService.getPautas.and.returnValue(of(mockPautasInfo));

    component.ngOnInit();

    expect(component.pautas).toEqual(mockPautasInfo);
    expect(pautaService.getPautas).toHaveBeenCalled();
  });

  it('should open add pauta modal and save pauta', () => {
    const dialogRefSpy = jasmine.createSpyObj({ afterClosed: of(mockPauta) });
    matDialog.open.and.returnValue(dialogRefSpy);
    pautaService.save.and.returnValue(of([mockPauta]));

    component.openAddPautaModal();

    expect(matDialog.open).toHaveBeenCalled();
    expect(dialogRefSpy.afterClosed).toHaveBeenCalled();
    expect(pautaService.save).toHaveBeenCalledWith(mockPauta);
    expect(component.pautas).toEqual([mockPautaInfo]);
  });

  it('should handle error when loading pautas', () => {
    const error = { message: 'Erro ao buscar pautas' };
    pautaService.getPautas.and.returnValue(throwError(error));

    component.carregarPautas();

    expect(component.pautas.length).toBe(0);
    expect(pautaService.getPautas).toHaveBeenCalled();
  });

  it('should handle error when saving pauta', () => {
    const dialogRefSpy = jasmine.createSpyObj({ afterClosed: of(mockPauta) });
    matDialog.open.and.returnValue(dialogRefSpy);
    const error = { message: 'Erro ao salvar pauta' };
    pautaService.save.and.returnValue(throwError(error));

    component.openAddPautaModal();

    expect(matDialog.open).toHaveBeenCalled();
    expect(dialogRefSpy.afterClosed).toHaveBeenCalled();
    expect(pautaService.save).toHaveBeenCalledWith(mockPauta);
    expect(component.pautas.length).toBe(0);
  });

  it('should initiate a new session', () => {
    const dialogRefSpy = jasmine.createSpyObj({ afterClosed: of(mockSessaoDTO) });
    matDialog.open.and.returnValue(dialogRefSpy);
    sessaoService.save.and.returnValue(of(mockSessaoDTO));

    component.iniciarSessao(mockPautaInfo.pautaId);

    expect(matDialog.open).toHaveBeenCalled();
    expect(dialogRefSpy.afterClosed).toHaveBeenCalled();
    expect(sessaoService.save).toHaveBeenCalledWith(mockSessaoDTO);
  });

  it('should handle error when initiating a new session', () => {
    const dialogRefSpy = jasmine.createSpyObj({ afterClosed: of(mockSessaoDTO) });
    matDialog.open.and.returnValue(dialogRefSpy);
    const error = { message: 'Erro ao iniciar sessão' };
    sessaoService.save.and.returnValue(throwError(error));

    component.iniciarSessao(mockPautaInfo.pautaId);

    expect(matDialog.open).toHaveBeenCalled();
    expect(dialogRefSpy.afterClosed).toHaveBeenCalled();
    expect(sessaoService.save).toHaveBeenCalledWith(mockSessaoDTO);
  });

  it('should vote and handle success', () => {
    const mockResult = { sucesso: true };
    const dialogRefSpy = jasmine.createSpyObj({ afterClosed: of(mockResult) });
    matDialog.open.and.returnValue(dialogRefSpy);

    component.votar(mockPautaInfo);

    expect(matDialog.open).toHaveBeenCalled();
    expect(dialogRefSpy.afterClosed).toHaveBeenCalled();
  });

  it('should vote and handle error', () => {
    const mockResult = { sucesso: false, mensagemErro: 'Erro ao votar' };
    const dialogRefSpy = jasmine.createSpyObj({ afterClosed: of(mockResult) });
    matDialog.open.and.returnValue(dialogRefSpy);

    component.votar(mockPautaInfo);

    expect(matDialog.open).toHaveBeenCalled();
    expect(dialogRefSpy.afterClosed).toHaveBeenCalled();
    expect(matSnackBar.open).toHaveBeenCalledWith('Erro ao votar', 'Fechar', {
      duration: 5000
    });
  });

  it('should return the correct session status', () => {
    component.now = new Date('2022-01-01T00:30:00');

    expect(component.getSessaoStatus(mockPautaInfo)).toBe('Em Andamento');

    component.now = new Date('2022-01-01T01:30:00');
    expect(component.getSessaoStatus(mockPautaInfo)).toBe('Encerrada');

    const mockPautaPendente: PautaInfo = {
      pautaId: 2,
      pautaTitulo: 'Pauta Pendente',
      pautaDescricao: 'Descrição da Pauta Pendente',
      sessaoId: 2,
      sessaoInicio: new Date(),
      sessaoDuracao: 0,
      sessaoTotalVotosSim: 0,
      sessaoTotalVotosNao: 0
    };

    expect(component.getSessaoStatus(mockPautaPendente)).toBe('Pendente');
  });
});
