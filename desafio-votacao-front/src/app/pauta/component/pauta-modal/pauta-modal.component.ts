import { Component } from '@angular/core';
import {MatDialogActions, MatDialogContent, MatDialogRef} from "@angular/material/dialog";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {FormsModule} from "@angular/forms";
import {MatInput} from "@angular/material/input";
import {MatButton} from "@angular/material/button";
import {PautaDTO} from "../../PautaDTO";

@Component({
  selector: 'app-add-pauta-modal',
  standalone: true,
  imports: [
    MatDialogContent,
    MatFormField,
    FormsModule,
    MatInput,
    MatDialogActions,
    MatButton,
    MatLabel
  ],
  templateUrl: './pauta-modal.component.html',
  styleUrl: './pauta-modal.component.css'
})
export class PautaModalComponent {
  novaPauta : PautaDTO = {id: null, titulo: '', descricao: ''};

  constructor(public dialogRef: MatDialogRef<PautaModalComponent>) { }

  onCancel(): void {
    this.dialogRef.close();
  }

  onSave(): void {
    console.log(JSON.stringify(this.novaPauta));
    this.dialogRef.close(this.novaPauta);
  }
}
