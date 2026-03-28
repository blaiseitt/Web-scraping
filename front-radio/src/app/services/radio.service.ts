import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RadioService {

  private STORAGE_KEY = 'selectedRadio';

  private radioSubject = new BehaviorSubject<string>(this.getInitialRadio());
  radio$ = this.radioSubject.asObservable();

  setRadio(radio: string) {
    localStorage.setItem(this.STORAGE_KEY, radio);
    this.radioSubject.next(radio);
  }

  private getInitialRadio(): string {
    return localStorage.getItem(this.STORAGE_KEY) || 'eska';
  }
}
