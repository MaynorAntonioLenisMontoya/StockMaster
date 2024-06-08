import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashboardRoutingModule } from './dashboard-routing.module';
import { DashboardComponent } from './dashboard.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './pages/home/home.component';
import { PrimeNgModule } from '../primeng/primeng.module';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { HomeCardComponent } from './components/home-card/home-card.component';


@NgModule({
  declarations: [
    DashboardComponent,
    NavbarComponent,
    HomeComponent,
    SidebarComponent,
    HomeCardComponent
  ],
  exports: [DashboardComponent],
  imports: [
    CommonModule,
    DashboardRoutingModule,
    PrimeNgModule,
  ]
})
export class DashboardModule { }
