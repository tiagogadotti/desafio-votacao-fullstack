import { Routes } from '@angular/router';
import {AssociadosComponent} from "./associados/associados.component";
import {PautasComponent} from "./pautas/pautas.component";

export const routes: Routes = [
  { path: 'associados', component: AssociadosComponent },
  { path: 'pautas', component: PautasComponent },
  { path: '', redirectTo: '/pautas', pathMatch: 'full' }
];
