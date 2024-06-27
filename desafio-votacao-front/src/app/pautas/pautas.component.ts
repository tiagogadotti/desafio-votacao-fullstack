import { Component, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { MatDialog } from "@angular/material/dialog";
import { MatCard, MatCardActions, MatCardContent, MatCardTitle } from "@angular/material/card";
import { NgForOf, NgIf } from "@angular/common";
import { MatButton } from "@angular/material/button";
import { AddPautaModalComponent } from "../add-pauta-modal/add-pauta-modal.component";
import { SessaoDTO, SessaoService } from "../services/sessao.service";
import { SessaoModal} from "../sessao-modal/sessao-modal-component.component";
import { PautaInfo, PautaService } from "../services/pauta.service";
import {VotacaoModalComponent} from "../votacao-modal/votacao-modal.component";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-pautas',
  templateUrl: './pautas.component.html',
  standalone: true,
  imports: [
    MatCardContent,
    MatCardTitle,
    MatCard,
    MatCardActions,
    NgForOf,
    MatButton,
    NgIf
  ],
  styleUrls: ['./pautas.component.css']
})
export class PautasComponent implements OnInit {
  pautas: PautaInfo[] = [];
  now: Date = new Date();
  errorMessage: string = '';

  constructor(
    public dialog: MatDialog,
    private pautaService: PautaService,
    private sessaoService: SessaoService,
    private snackBar: MatSnackBar

  ) { }

  ngOnInit(): void {
    this.carregarPautas();
  }

  iniciarSessao(pautaId: number | null) {
    console.log('Iniciando sessão para a pauta:', pautaId);

    const dialogRef = this.dialog.open(SessaoModal, {
      width: '250px',
      data: { pautaId }
    });

    dialogRef.afterClosed().subscribe((result: SessaoDTO) => {
      if (result) {
        this.sessaoService.save(result).subscribe({
          next: value => {
            window.location.reload();
          },
          error: error => {
            console.log("Erro ao iniciar sessão: " + JSON.stringify(error));
          }
        });
      }
    });
  }

  getSessaoStatus(pauta: PautaInfo): string {
    if (!pauta.sessaoInicio) {
      return 'Pendente';
    }
    const inicio: Date = new Date(pauta.sessaoInicio);
    const termino: Date = new Date(inicio.getTime() + pauta.sessaoDuracao * 60000);

    if (termino < this.now) {
      return 'Encerrada';
    } else if (inicio <= this.now && this.now < termino) {
      return 'Em Andamento';
    } else {
      return 'Pendente';
    }
  }

  openAddPautaModal(): void {
    const dialogRef = this.dialog.open(AddPautaModalComponent, {
      width: '300px'
    });

    dialogRef.afterClosed().subscribe(pauta => {
      console.log(pauta);
      if (pauta) {
        this.pautaService.save(pauta).subscribe({
          next: pautas => {
            this.pautas = pautas;
          },
          error: err => console.log("Erro ao salvar Pauta: " + err)
        });
      }
    });
  }

  carregarPautas(): void {
    this.pautaService.getPautas().subscribe({
      next: pautas => {
        this.pautas = pautas;
        console.log("Carregou pautas: " +this.pautas);
      },
      error: error => {
        console.log("Erro ao buscar pautas: " + JSON.stringify(error));
      }
    });
  }

  votar(pautaInfo: PautaInfo) {
    console.log("PautaInfo: " +JSON.stringify(pautaInfo))
    const dialogRef = this.dialog.open(VotacaoModalComponent, {
      width: '250px',
      data: { pautaInfo }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result && result.sucesso === true) {
          window.location.reload();
      } else if (result && result.sucesso === false) {
        //this.errorMessage = result.mensagemErro;
        this.snackBar.open(result.mensagemErro, 'Fechar', {
          duration: 5000
        })
      }
    });
  }
}
