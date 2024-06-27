import {Component, OnInit} from '@angular/core';
import {AssociadoService} from "../services/associado.service";
import {MatDialog} from "@angular/material/dialog";
import {NgForOf} from "@angular/common";
import {AssociadoModalComponent} from "./associado-modal/associado-modal.component";
import {MatButton} from "@angular/material/button";
import {Associado} from "../Associado";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-associados',
  standalone: true,
  imports: [
    NgForOf,
    MatButton
  ],
  templateUrl: './associado.component.html',
  styleUrl: './associado.component.css'
})
export class AssociadoComponent implements OnInit {

  associados: Associado[] = [];
  mensagemErro: string = '';

  constructor(private associadoService: AssociadoService,
              public dialog: MatDialog,
              private snackBar: MatSnackBar
  ) { }

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
    const dialogRef = this.dialog.open(AssociadoModalComponent, {
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
            this.snackBar.open(erro.error.message, 'Fechar', {duration: 5000});
          }
        });
      }
    });
  }
}
