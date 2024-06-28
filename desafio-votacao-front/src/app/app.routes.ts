import { Routes } from '@angular/router';
import {AssociadoComponent} from "./associado/component/associado.component";
import {PautaComponent} from "./pauta/component/pauta.component";
import {IndexComponent} from "./index/index.component";

export const routes: Routes = [
  { path: 'associados', component: AssociadoComponent },
  { path: 'pautas', component: PautaComponent },
  { path: '',  component: IndexComponent}
];
