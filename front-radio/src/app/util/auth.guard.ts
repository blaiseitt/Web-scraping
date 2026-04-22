import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { map } from 'rxjs';

export const adminGuard: CanActivateFn = () => {
  const auth = inject(AuthService);
  const router = inject(Router);

  return auth.checkAuth().pipe(
    map(result => {
      if (result === null) {
        router.navigate(['/unauthorized']);
        return false;
      }
      return true;
    })
  );
};