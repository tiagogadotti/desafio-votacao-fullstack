import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {BASE_URL} from "../../config/constants";
import {SessaoDTO} from "../SessaoDTO";



@Injectable({
  providedIn: 'root'
})
export class SessaoService {

  constructor(private httpClient: HttpClient) { }

  save(sessaoDTO: SessaoDTO): Observable<any> {
      return this.httpClient.post(`${BASE_URL}/sessao`, sessaoDTO);
  }

}
