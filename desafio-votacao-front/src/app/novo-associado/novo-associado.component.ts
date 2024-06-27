import { Component } from '@angular/core';
import {
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from "@angular/material/dialog";
import {AssociadoService} from "../services/associado.service";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {FormsModule} from "@angular/forms";
import {MatButton} from "@angular/material/button";
import {MatInput} from "@angular/material/input";

@Component({
  selector: 'app-novo-associado',
  standalone: true,
  imports: [
    MatDialogTitle,
    MatDialogContent,
    MatFormField,
    FormsModule,
    MatDialogClose,
    MatButton,
    MatDialogActions,
    MatInput,
    MatLabel
  ],
  templateUrl: './novo-associado.component.html',
  styleUrl: './novo-associado.component.css'
})
export class NovoAssociadoComponent {
  associado = { nome: '', cpf: '', aptoParaVotar: true };

  constructor(
    public dialogRef: MatDialogRef<NovoAssociadoComponent>,
    private associadoService: AssociadoService
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  generateCPF(): void {
    this.associado.cpf = this.associadoService.generateCPF();
  }
}
