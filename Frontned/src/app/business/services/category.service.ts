import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Category, Result } from '../interfaces/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private baseUrl = environment.baseUrl;

  constructor(
    private http: HttpClient
  ) { }


  getcategories(size: number, page: number): Observable<Result> {
    return this.http.get<Result>(`${this.baseUrl}/get-categories?pageSize=${size}&pageNumber=${page}`);
  }

  saveCategory(data: Category):Observable<Result> {
    return this.http.post<Result>(`${this.baseUrl}/create-category`, data);
  }

  updateCategory(data: Category):Observable<Result> {
    return this.http.put<Result>(`${this.baseUrl}/update-category`, data);
  }

  deleteById(id: number): Observable<Result> {
    return this.http.delete<Result>(`${this.baseUrl}/delete/category/${id}`);
  }

  findAll(): Observable<Result> {
    return this.http.get<Result>(`${this.baseUrl}/get-all-categories`);
  }


}
