import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {BASE_URL} from "../../config/constants";
import {VotoDTO} from "../VotoDTO";






@Injectable({
  providedIn: 'root'
})
export class VotoService {

  constructor(private httpClient: HttpClient) {}

  save(voto: VotoDTO): Observable<any> {
    return this.httpClient.post(`${BASE_URL}/voto`, voto);
  }
}
