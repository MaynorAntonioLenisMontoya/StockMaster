import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { AuthStatus } from '../interfaces';
import { inject } from '@angular/core';

export const isNotAuthenticatedGuard: CanActivateFn = (route, state) => {

  const authService = inject( AuthService );
  const router = inject(Router);

  console.log(authService.authStatus());


  if(authService.authStatus() === AuthStatus.authenticated){
    router.navigateByUrl('/dashboard/home');
    return false;
  }
  authService.logout();
  return true;
};
