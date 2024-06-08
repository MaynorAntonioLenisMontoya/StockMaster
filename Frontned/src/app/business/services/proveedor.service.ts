import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Proveedor, Result } from '../interfaces/proveedor';

@Injectable({
  providedIn: 'root'
})
export class ProveedorService {

  private baseUrl = environment.baseUrl;

  constructor(
    private http: HttpClient
  ) { }

  getProveedores(size: number, page: number):Observable<Result> {
    return this.http.get<Result>(`${this.baseUrl}/get-provedores?pageSize=${size}&pageNumber=${page}`)
  }

  saveProveedor(data: Proveedor): Observable<Result> {
    return this.http.post<Result>(`${this.baseUrl}/create-proveedor`, data);
  }

  updateProveedor(data: Proveedor): Observable<Result> {
    return this.http.put<Result>(`${this.baseUrl}/update-proveedor`, data);
  }

  deleteById(id: number):Observable<Result> {
    return this.http.delete<Result>(`${this.baseUrl}/delete-proveedor/${id}`);
  }

}
