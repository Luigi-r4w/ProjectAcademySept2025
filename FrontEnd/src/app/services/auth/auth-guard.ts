import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { Auth } from './auth';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(Auth)
  const router = inject(Router)
  console.log(authService.isAutentificated())
  if(!authService.isAutentificated()){
    router.navigate(['login'])
  }
  return authService.isAutentificated();
};
