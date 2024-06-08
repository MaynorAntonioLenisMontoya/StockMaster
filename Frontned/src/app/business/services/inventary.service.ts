import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, map, of } from 'rxjs';
import { Inventary, Result } from '../interfaces/inventary';

@Injectable({
  providedIn: 'root'
})
export class InventaryService {

  private baseUrl = environment.baseUrl;

  constructor(
    private http: HttpClient
  ) { }


  getInventaryPageable(pageSize: number, pageNumber: number): Observable<Result> {
    console.log('Ingreso a services');

    return this.http.get<Result>(`${this.baseUrl}/get-inventary?pageSize=${pageSize}&pageNumber=${pageNumber}`)
  }

  saveInventary(inventary: Inventary): Observable<Result> {
    console.log('Inicia metodo saveInventary');

    return this.http.post<Result>(`${this.baseUrl}/create-inventary`, inventary)

  }

  updateInventary(inventary: Inventary): Observable<Result> {
    console.log('Inicia metodo saveInventary');

    return this.http.put<Result>(`${this.baseUrl}/update-inventary`, inventary)

  }

  deleteInventaryById(id: number): Observable<Result> {
    return this.http.delete<Result>(`${this.baseUrl}/delete-inventary/${id}`)
  }

  getFindAll(): Observable<Result> {
    return this.http.get<Result>(`${this.baseUrl}/get-all-inventary`);
  }

}
