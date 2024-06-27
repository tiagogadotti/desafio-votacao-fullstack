import { Component, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { MatDialog } from "@angular/material/dialog";
import { MatCard, MatCardActions, MatCardContent, MatCardTitle } from "@angular/material/card";
import {NgClass, NgForOf, NgIf} from "@angular/common";
import { MatButton } from "@angular/material/button";
import { PautaModalComponent } from "./pauta-modal/pauta-modal.component";
import { SessaoService } from "../services/sessao.service";
import { SessaoModal} from "./sessao-modal/sessao-modal-component.component";
import { PautaService } from "../services/pauta.service";
import {VotacaoModalComponent} from "./votacao-modal/votacao-modal.component";
import {MatSnackBar} from "@angular/material/snack-bar";
import {PautaInfo} from "../PautaInfo";
import {SessaoDTO} from "../SessaoDTO";
import {MatIcon} from "@angular/material/icon";

@Component({
  selector: 'app-pautas',
  templateUrl: './pauta.component.html',
  standalone: true,
  imports: [
    MatCardContent,
    MatCardTitle,
    MatCard,
    MatCardActions,
    NgForOf,
    MatButton,
    NgIf,
    NgClass,
    MatIcon
  ],
  styleUrls: ['./pauta.component.css']
})
export class PautaComponent implements OnInit {
  pautas: PautaInfo[] = [];
  now: Date = new Date();

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
    const dialogRef = this.dialog.open(PautaModalComponent, {
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
        console.log("Carregou pautas: " +JSON.stringify(this.pautas));
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
        this.snackBar.open(result.mensagemErro, 'Fechar', {
          duration: 5000
        })
      }
    });
  }

  getStatusClass(pauta: any): string {
    const status = this.getSessaoStatus(pauta);
    return status === 'Em Andamento' ? 'EmAndamento' : status;
  }
}
