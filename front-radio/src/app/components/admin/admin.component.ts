import { HttpClient } from "@angular/common/http";
import { Component, OnInit, signal } from "@angular/core";

@Component({
  selector: 'app-admin',
  standalone: true,
  template: `
    <div>
      <h2>Admin Dashboard</h2>
      @if (data()) {
        <p>{{ data().message }}</p>
      }
    </div>
  `
})
export class AdminComponent implements OnInit {
  data = signal<any>(null);

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.http.get('http://localhost:8080/admin/dashboard', { withCredentials: true })
      .subscribe(res => this.data.set(res));
  }
}