import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {BASE_URL} from "../../config/constants";

@Injectable({
  providedIn: 'root'
})
export class CpfService {

  constructor(private httpClient: HttpClient) { }

  validarCpf(cpf: string): Observable<any> {
    return this.httpClient.post(`${BASE_URL}/cpf/validar`, cpf);
  }
}
