import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AppNavbar } from './navbar/navbar.component';
import { SongsTable } from './songsTable/songsTable.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, AppNavbar, SongsTable],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})

export class AppComponent {
  title = 'Znajdź swoją piosenkę';
  currentRadio = 'eska';

  onRadioChange(radio: string) {
    this.currentRadio = radio;
  }
}
