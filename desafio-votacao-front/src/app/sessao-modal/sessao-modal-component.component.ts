import { Component, Inject } from '@angular/core';
import {
  MatDialogRef,
  MAT_DIALOG_DATA,
  MatDialogTitle,
  MatDialogActions,
  MatDialogContent
} from '@angular/material/dialog';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {SessaoDTO} from "../services/sessao.service";
import {MatFormField} from "@angular/material/form-field";
import {MatButton} from "@angular/material/button";
import {MatInput} from "@angular/material/input";

@Component({
  standalone: true,
  templateUrl: "./sessao-modal-component.component.html",
  imports: [
    ReactiveFormsModule,
    MatFormField,
    MatDialogTitle,
    MatDialogActions,
    MatButton,
    MatDialogContent,
    MatInput
  ],
  selector: 'app-sessao-modal'
})
export class SessaoModal {
  form: FormGroup;

  constructor(
    public dialogRef: MatDialogRef<SessaoModal>,
    @Inject(MAT_DIALOG_DATA) public data: { pautaId: number},
    private fb: FormBuilder
  ) {
      this.form = this.fb.group({
        duracao: [1]
    });
  }

  onSave(): void {
    if (this.form.valid) {
      const sessao: SessaoDTO = {
        id: null,
        pautaId: this.data.pautaId,
        inicio: null,
        duracao: this.form.value.duracao
      };
      console.log("onSave: " + JSON.stringify(sessao));
      this.dialogRef.close(sessao);
    }
  }

  onCancel(): void {
    this.dialogRef.close();
  }
}
