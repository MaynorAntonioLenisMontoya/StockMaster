import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { DashboardComponent } from './dashboard.component';
import { isAuthenticatedGuard } from '../auth/guards';

const routes: Routes = [
  {
    path: '',
    component: DashboardComponent,
    canActivate: [isAuthenticatedGuard],
    children: [
      {
        path: 'home',
        canActivate: [isAuthenticatedGuard],
        component: HomeComponent
      },
      {
        path: '**',
        redirectTo: 'home'
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
