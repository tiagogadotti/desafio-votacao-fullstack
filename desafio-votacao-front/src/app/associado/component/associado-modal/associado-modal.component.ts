import { Component } from '@angular/core';
import {
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from "@angular/material/dialog";
import {AssociadoService} from "../../services/associado.service";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {FormsModule} from "@angular/forms";
import {MatButton} from "@angular/material/button";
import {MatInput} from "@angular/material/input";
import {CpfService} from "../../services/cpf.service";
import {NgIf} from "@angular/common";

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
    MatLabel,
    NgIf
  ],
  templateUrl: './associado-modal.component.html',
  styleUrl: './associado-modal.component.css'
})
export class AssociadoModalComponent {
  associado = { nome: '', cpf: '', aptoParaVotar: true };
  mensagemValidacaoCPF: string = '';

  constructor(
    public dialogRef: MatDialogRef<AssociadoModalComponent>,
    private associadoService: AssociadoService,
    private cpfService: CpfService
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  generateCPF(): void {
    this.associado.cpf = this.associadoService.generateCPF();
  }

  validarCPF() {
    this.cpfService.validarCpf(this.associado.cpf).subscribe ({
      next: (result) =>{
        this.mensagemValidacaoCPF = 'CPF VÁLIDO\n';
        this.mensagemValidacaoCPF += result.status == "ABLE_TO_VOTE" ? 'Apto a Votar' : 'Inapto a votar';
      },
      error: err => {
        console.log(err);
        this.mensagemValidacaoCPF = 'CPF INVÁLIDO \n';
      }
    });
  }
}
