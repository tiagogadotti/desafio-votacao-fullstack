import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {BASE_URL} from "../config/constants";

export interface VotoDTO {
  associadoId: number;
  sessaoId: number;
  opcao: Opcao;
}

enum Opcao{
  SIM = "SIM",NAO = "NÃO"
}


@Injectable({
  providedIn: 'root'
})
export class VotoService {

  constructor(private httpClient: HttpClient) {}

  save(voto: VotoDTO): Observable<any> {
    return this.httpClient.post(`${BASE_URL}/voto`, voto);
  }
}
