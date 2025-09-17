import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { Auth } from './auth';

export const authAdminGuard: CanActivateFn = (route, state) => {
  const authService = inject(Auth);
  return authService.getIsAdmin();
};
