import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Result, TipoTransicion } from '../interfaces/tipo-transicion';

@Injectable({
  providedIn: 'root'
})
export class TipoTransicionService {

  private baseUrl = environment.baseUrl;

  constructor(
    private http: HttpClient
  ) { }


  getTipoTransicionPageable(size: number, page: number): Observable<Result> {
    return this.http.get<Result>(`${this.baseUrl}/get-tipo-transicion?pageSize=${size}&pageNumber=${page}`)
  }

  saveTipoTransicion(data: TipoTransicion): Observable<Result> {
    return this.http.post<Result>(`${this.baseUrl}/create-tipo-transicion`, data);
  }

  updateTipoTransicion(data: TipoTransicion): Observable<Result> {
    return this.http.put<Result>(`${this.baseUrl}/update-tipo-transicion`, data);
  }

  deleteById(id: number): Observable<Result> {
    return this.http.delete<Result>(`${this.baseUrl}/delete-tipo-transicion/${id}`);
  }
}
