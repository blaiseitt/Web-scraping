import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { AdminComponent } from './components/admin/admin.component';
import { adminGuard } from './util/auth.guard';
import { UnauthorizedComponent } from './components/unauthorized/unauthorized.component';

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
    },
    {
        path: 'admin',
        component: AdminComponent,
        title: 'Admin',
        canActivate: [adminGuard]
    },
    {
        path: 'unauthorized',
        component: UnauthorizedComponent,
        title: 'Unauthorized'
    }
];

export default routeConfig;