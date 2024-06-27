import {Component, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BASE_URL} from "../../config/constants";
import {Observable} from "rxjs";
import {Associado} from "../Associado";



@Injectable({
  providedIn: 'root'
})
export class AssociadoService {
  private associados: Associado[] = [
    { id: 1, nome: 'João Silva', cpf: '123.456.789-09', aptoParaVotar: true },
    { id: 2, nome: 'Maria Oliveira', cpf: '987.654.321-09', aptoParaVotar: false },
  ];


  constructor(private httpClient: HttpClient) { }

  getAssociados(): Observable<any> {
    return this.httpClient.get(`${BASE_URL}/associados`);
  }

  addAssociado(associado: Associado): Observable<any> {
    return this.httpClient.post( `${BASE_URL}/associado`, associado);
  }

  generateCPF(): string {
    const rand = (n: number) => Math.round(Math.random() * n);
    const mod = (base: number, mod: number) => Math.round(base - (Math.floor(base / mod) * mod));
    let cpf = '';
    let i;
    for(i = 0; i < 9; i++) {
      cpf += rand(9);
    }

    let digit = 0;
    for (i = 0; i < 9; i++) {
      digit += parseInt(cpf[i]) * (10 - i);
    }
    digit = 11 - mod(digit % 11, 10);
    cpf += (digit === 10 || digit === 11) ? 0 : digit;

    digit = 0;
    for (i = 0; i < 10; i++) {
      digit += parseInt(cpf[i]) * (11 - i);
    }
    digit = 11 - mod(digit % 11, 10);
    cpf += (digit === 10 || digit === 11) ? 0 : digit;

    return cpf;
  }
}
