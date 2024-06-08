import { Injectable, computed, inject, signal } from '@angular/core';
import { Observable, catchError, map, of, throwError } from 'rxjs';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { AuthStatus, LoginResponse, Result, User } from '../interfaces';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly baseUrl: string = environment.baseUrl;

  private http =  inject( HttpClient );

  private _currentUser = signal<User|null>( null );

  private _authStatus = signal<AuthStatus>( AuthStatus.checking );

  //! Al mundo exterior
  public currentUser = computed( () => this._currentUser() );
  public authStatus = computed( () => this._authStatus() );

  constructor() {
    this.checkAuthStatus().subscribe();
  }

  login(username: string, password: string): Observable<boolean> {
    const url = `${this.baseUrl}/login`;
    const body = { username, password };

    return this.http.post<LoginResponse>(url, body).pipe(
      map((response: LoginResponse) => this.setAuthentication(response.result.token)),
      catchError(err => throwError(() => err.error.message))
    );
  }

  private setAuthentication(token: string): boolean {
    this._authStatus.set(AuthStatus.authenticated);
    localStorage.setItem('token', token);
    return true;
  }

  checkAuthStatus(): Observable<boolean> {
    const token = localStorage.getItem('token');

    if (!token) {
      this.logout();
      return of(false);
    }

    const isTokenExpired = this.isTokenExpired(token);
    if (isTokenExpired) {
      this.logout();
      return of(false);
    }

    return of(true);
  }

  private isTokenExpired(token: string): boolean {
    try {
      const decoded = this.decodeToken(token);
      const expirationDate = decoded.exp * 1000; // Convertir a milisegundos
      const currentDate = new Date().getTime();

      return expirationDate < currentDate;
    } catch (error) {
      console.error('Error decoding token:', error);
      return true;
    }
  }

  private decodeToken(token: string): any {
    const payload = token.split('.')[1];
    const decodedPayload = atob(payload);
    return JSON.parse(decodedPayload);
  }



  logout() {
    localStorage.removeItem('token');
    this._currentUser.set(null);
    this._authStatus.set(AuthStatus.notAuthenticated);
  }
}
