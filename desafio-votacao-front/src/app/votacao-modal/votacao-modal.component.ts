import {Component, Inject} from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from "@angular/material/dialog";
import {PautaInfo} from "../services/pauta.service";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {AssociadoService} from "../services/associado.service";
import {VotoDTO, VotoService} from "../services/voto.service";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatOption, MatSelect} from "@angular/material/select";
import {MatRadioButton, MatRadioGroup} from "@angular/material/radio";
import {NgForOf} from "@angular/common";
import {MatButton} from "@angular/material/button";

@Component({
  selector: 'app-votacao-modal',
  standalone: true,
  imports: [
    MatDialogContent,
    MatDialogTitle,
    MatLabel,
    MatFormField,
    MatSelect,
    MatOption,
    MatRadioButton,
    NgForOf,
    MatRadioGroup,
    MatDialogActions,
    MatButton,
    ReactiveFormsModule
  ],
  templateUrl: './votacao-modal.component.html',
  styleUrl: './votacao-modal.component.css'
})
export class VotacaoModalComponent {

  form: FormGroup;
  associados: any[] = [];
  errorMessage: string = '';

  constructor(
    public dialogRef: MatDialogRef<VotacaoModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder,
    private associadoService: AssociadoService,
    private votoService: VotoService
  ) {
    this.form = this.fb.group({
      associadoId: [null, Validators.required],
      voto: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    this.associadoService.getAssociados().subscribe(associados => {
      this.associados = associados;
    });
  }

  onVote(): void {
    if (this.form.valid) {
      const voto: VotoDTO = {
        associadoId: this.form.value.associadoId,
        sessaoId: this.data.pautaInfo.sessaoId,
        opcao: this.form.value.voto
      };

      this.votoService.save(voto).subscribe({
        next: () => {
          this.dialogRef.close({sucesso: true});
        },
        error: (err) => {
          this.errorMessage = err.error.message || 'Erro ao votar';
          this.dialogRef.close({sucesso:false, mensagemErro: this.errorMessage})
        }
      });
    }
  }

  onCancel(): void {
    this.dialogRef.close({ sucesso: false });
  }

}
