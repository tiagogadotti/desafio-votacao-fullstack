import { Routes } from '@angular/router';
import {AssociadoComponent} from "./associado/component/associado.component";
import {PautaComponent} from "./pauta/component/pauta.component";

export const routes: Routes = [
  { path: 'associados', component: AssociadoComponent },
  { path: 'pautas', component: PautaComponent },
  { path: '', redirectTo: '/pautas', pathMatch: 'full' }
];
