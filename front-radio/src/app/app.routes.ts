import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';

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