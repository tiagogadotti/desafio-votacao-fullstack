import { Injectable } from '@angular/core';
import {Observable, switchAll, switchMap} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {BASE_URL} from "../../config/constants";
import {PautaDTO} from "../PautaDTO";


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


