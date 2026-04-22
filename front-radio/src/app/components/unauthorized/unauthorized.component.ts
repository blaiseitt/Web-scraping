import { Component } from "@angular/core";
import { RouterLink } from "@angular/router";

@Component({
  selector: 'app-unauthorized',
  standalone: true,
  template: `
    <div class="unauthorized-container">
      <h2>403 — Access Denied</h2>
      <p>You don't have permission to view this page.</p>
      <a routerLink="/">Go home</a>
    </div>
  `,
  imports: [RouterLink]
})
export class UnauthorizedComponent {}