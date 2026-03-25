import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';

const routeConfig: Routes = [
    {
        path: '',
        component: HomeComponent,
        title: 'Find song'
    },
    {
        path: 'login',
        component: LoginComponent,
        title: 'Login'
    }
];

export default routeConfig;