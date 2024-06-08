import { DOCUMENT } from '@angular/common';
import { Component, Inject, inject } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { AuthService } from '../../../auth/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  public isSidebarActive: boolean = false;
  public menuItems: MenuItem[] | undefined;
  public items: MenuItem[] | undefined;
  public isDarkMode: boolean = false;
  public themeSelection: boolean = false;

  private authService = inject(AuthService);
  private router = inject(Router);

  constructor(
    @Inject(DOCUMENT) private document: Document
  ) {
    // IsDarkModeActive
    let theme = window.localStorage.getItem('theme');
    if ( theme ) {
      this.themeSelection = theme == 'dark' ? true : false;
      this.toggleMode(this.themeSelection);
      this.isDarkMode = !this.isDarkMode;
    }

    this.items = [
      {
          label: 'Opciones',
          items: [
              {
                  label: 'Perfil',
                  icon: 'pi pi-cog'
              },
              {
                  label: 'Cerrar sesion',
                  icon: 'pi pi-sign-out',
                  command: () => {
                    this.authService.logout();
                    this.router.navigate(['/auth/login']);
                }
              }
          ]
      }
    ];

  }

  showSidebar() {
    this.isSidebarActive = !this.isSidebarActive;
  }

  toggleMode(state: boolean) {
    console.log(state);

    let theme = state ? 'dark' : 'light';
    window.localStorage.setItem('theme', theme);
    let themeLink = this.document.getElementById('app-theme') as HTMLLinkElement;
    themeLink.href = `theme-${theme}.css`;
    this.isDarkMode = !this.isDarkMode;
  }



}
