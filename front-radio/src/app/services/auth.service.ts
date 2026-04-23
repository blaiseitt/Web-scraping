import { HttpClient } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { catchError, of, tap } from 'rxjs';
import { NotificationService } from './notification.service';

@Injectable({ providedIn: 'root' })
export class AuthService {
  isLoggedIn = signal(false);
  private apiUrl = 'http://localhost:8080';

  constructor(
    private http: HttpClient,
    private notify: NotificationService
  ) {}

  login(username: string, password: string) {
    const body = new URLSearchParams();
    body.set('username', username);
    body.set('password', password);

    return this.http.post(`${this.apiUrl}/login`, body.toString(), {
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
      withCredentials: true
    }).pipe(
      tap(() => {
        this.isLoggedIn.set(true);
        this.notify.success('Logged in successfully');
      })
    );
  }

  logout() {
    return this.http.post(`${this.apiUrl}/logout`, {}, { withCredentials: true }).pipe(
      tap(() => {
        this.isLoggedIn.set(false);
        this.notify.success('Logged out successfully');
      })
    );
  }

  // TODO check if has role admin not only check any role
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