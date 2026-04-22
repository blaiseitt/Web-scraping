import { HttpClient } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { catchError, of, tap } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {
  isLoggedIn = signal(false);
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  login(username: string, password: string) {
    const body = new URLSearchParams();
    body.set('username', username);
    body.set('password', password);

    return this.http.post(`${this.apiUrl}/login`, body.toString(), {
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
      withCredentials: true
    }).pipe(
      tap(() => this.isLoggedIn.set(true))
    );
  }

  logout() {
    return this.http.post(`${this.apiUrl}/logout`, {}, { withCredentials: true }).pipe(
      tap(() => this.isLoggedIn.set(false))
    );
  }

  checkAuth() {
    return this.http.get(`${this.apiUrl}/api/me`, { withCredentials: true }).pipe(
      tap(() => this.isLoggedIn.set(true)),
      catchError(() => {
        this.isLoggedIn.set(false);
        return of(null);
      })
    );
  }
}