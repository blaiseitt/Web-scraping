import { Component } from '@angular/core';
import { AppNavbar } from '../navbar/navbar.component';
import { SongsTable } from '../songsTable/songsTable.component';

@Component({
  selector: 'app-home',
  imports: [AppNavbar, SongsTable],
  standalone: true,
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})

export class HomeComponent {
  title = 'Find your song';
  currentRadio = 'eska';

  onRadioChange(radio: string) {
    this.currentRadio = radio;
  }
}
