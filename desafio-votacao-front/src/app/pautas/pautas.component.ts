import { Component, OnInit } from '@angular/core';
import {MatCard, MatCardActions, MatCardContent, MatCardTitle} from "@angular/material/card";
import {NgForOf, NgIf} from "@angular/common";
import {MatButton} from "@angular/material/button";
import {AddPautaModalComponent} from "../add-pauta-modal/add-pauta-modal.component";
import {MatDialog} from "@angular/material/dialog";
import {MatLabel} from "@angular/material/form-field";
import {PautaDTO, PautaInfo, PautaService} from "../services/pauta.service";
import {SessaoDTO, SessaoService} from "../services/sessao.service";

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
    MatLabel,
    NgIf
  ],
  styleUrls: ['./pautas.component.css']
})
export class PautasComponent implements OnInit {
  pautas: PautaInfo[] = [];
  now: Date = new Date();

  constructor(public dialog: MatDialog,
              private pautaService: PautaService,
              private sessaoService: SessaoService
  ) { }

  ngOnInit(): void {
    this.pautaService.getPautas().subscribe({
      next: pautas => {
        this.pautas = pautas;
        console.log(this.pautas);
      },
      error: error => {
        console.log("Erro ao buscar pautas: " + JSON.stringify(error));
      }
    });
  }

  iniciarSessao(id: number | null) {
    console.log('Iniciando sessão para a pauta:', id);
  }

  getSessaoStatus(pauta: PautaInfo): string {
    if (!pauta.sessaoId || !pauta.sessaoInicio) {
      return 'Pendente';
    }
    const inicio : Date = new Date(pauta.sessaoInicio);
    const termino : Date = new Date(inicio.getTime() + pauta.sessaoDuracao * 60000);
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
      if (pauta) {
        this.pautaService.save(pauta).subscribe({
          next: pauta =>{
            this.pautas.push(pauta);
          },
          error: err => console.log("Erro ao salvar Pauta: " +err)
        });
      }
    });
  }
}
