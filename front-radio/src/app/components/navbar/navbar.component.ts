import {Component} from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import { RadioService } from '../../services/radio.service';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';


@Component({
    selector: 'app-navbar',
    standalone: true,
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.scss'],
    imports: [MatIconModule, MatButtonModule, MatToolbarModule, FormsModule, MatFormFieldModule, MatInputModule]
})

 export class AppNavbar {

    activeRadio = '';

    constructor(private radioService: RadioService, public authService: AuthService, private router: Router) {}
    
    ngOnInit() {
        this.radioService.radio$.subscribe(radio => {
            this.activeRadio = radio;
        })
    }

    selectRadio(radio: string) {
        this.radioService.setRadio(radio);
    }

    login() {
        this.router.navigate(['/login']);
    }

    logout() {
        this.authService.logout().subscribe(() => this.router.navigate(['/']));
    }

    adminPanel() {
        this.router.navigate(['/admin']);
    }
}