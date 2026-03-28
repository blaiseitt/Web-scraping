import {Component, EventEmitter, Input, Output} from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';


@Component({
    selector: 'app-navbar',
    standalone: true,
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.scss'],
    imports: [MatIconModule, MatButtonModule, MatToolbarModule, FormsModule, MatFormFieldModule, MatInputModule]
})

 export class AppNavbar {

    @Input() activeRadio!: string;
    @Output() radioSelected = new EventEmitter<string>();
    
    selectRadio(radio: string) {
        this.radioSelected.emit(radio);
    }
}