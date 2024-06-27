import { Injectable } from '@angular/core';
import {Observable, switchAll, switchMap} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {BASE_URL} from "../config/constants";


@Injectable({
  providedIn: 'root'
})
export class PautaService {

  constructor(private httpClient: HttpClient) { }

  getPautas(): Observable<any> {
    return this.httpClient.get(`${BASE_URL}/pautas/info`);
  }

  save(pauta: PautaDTO): Observable<any> {
    return this.httpClient.post(`${BASE_URL}/pauta`, pauta)
      .pipe(
        switchMap( () => this.getPautas())
      );
  }
}

export interface PautaDTO {
  id: number | null,
  titulo: string,
  descricao: string
}
export interface PautaInfo {
  pautaId: number,
  pautaTitulo: string,
  pautaDescricao: string,
  sessaoId: number,
  sessaoInicio: Date,
  sessaoDuracao: number,
  sessaoTotalVotosSim: number,
  sessaoTotalVotosNao: number
}
