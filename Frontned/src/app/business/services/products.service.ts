import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { Producto, Result, Result_Product } from '../interfaces/product';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  private baseUrl = environment.baseUrl;

  constructor(
    private http: HttpClient
  ) { }

  getInventaryProduct(size: number, page: number): Observable<Result> {
    return this.http.get<Result>(`${this.baseUrl}/get-inventary-products?pageSize=${size}&pageNumber=${page}`);
  }

  saveProduct(data: Producto): Observable<Result> {
    return this.http.post<Result>(`${this.baseUrl}/create-product`, data);
  }

  getProductByInventary(inventaryId: number, size: number, page: number): Observable<Result_Product> {
    return this.http.get<Result_Product>(`${this.baseUrl}/get-products/${inventaryId}?pageSize=${size}&pageNumber=${page}`);
  }

  updateProduct(data: Producto): Observable<Result> {
    return this.http.put<Result>(`${this.baseUrl}/update-product`, data);
  }

  deleteById(id: number):Observable<Result> {
    return this.http.delete<Result>(`${this.baseUrl}/delete-product/${id}`);
  }



}
