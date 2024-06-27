import {Component, OnInit} from '@angular/core';
import {Associado, AssociadoService} from "../services/associado.service";
import {MatDialog} from "@angular/material/dialog";
import {NgForOf} from "@angular/common";
import {NovoAssociadoComponent} from "../novo-associado/novo-associado.component";
import {MatButton} from "@angular/material/button";

@Component({
  selector: 'app-associados',
  standalone: true,
  imports: [
    NgForOf,
    MatButton
  ],
  templateUrl: './associados.component.html',
  styleUrl: './associados.component.css'
})
export class AssociadosComponent implements OnInit {

  associados: Associado[] = [];

  constructor(private associadoService: AssociadoService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.associadoService.getAssociados().subscribe({
      next: associados => {
        this.associados = associados;
      },
      error: error => {
        console.log('Erro ao carregar associados ' + JSON.stringify(error));
      }
    })
  }

  openAddAssociadoModal(): void {
    const dialogRef = this.dialog.open(NovoAssociadoComponent, {
      width: '250px'
    });

    dialogRef.afterClosed().subscribe(novoAssociado => {
      if (novoAssociado) {
        this.associadoService.addAssociado(novoAssociado).subscribe({
          next: (novoAssociado) => {
            this.associados.push(novoAssociado);
          },
          error: (erro) => {
            console.error('Erro ao adicionar associado:', JSON.stringify(erro));
          }
        });
      }
    });
  }
}
