import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InventarioComponent } from './pages/inventario/inventario.component';
import { CategoriasComponent } from './pages/categorias/categorias.component';
import { ProductosComponent } from './pages/productos/productos.component';
import { ReportesComponent } from './pages/reportes/reportes.component';
import { ProveedoresComponent } from './pages/proveedores/proveedores.component';
import { ProductosDetailsComponent } from './pages/productos-details/productos-details.component';
import { TransicionComponent } from './pages/transicion/transicion.component';
import { TransaccionComponent } from './pages/transaccion/transaccion.component';
import { BusinessLayoutComponent } from './layouts/business-layout/business-layout.component';
import { isAuthenticatedGuard } from '../auth/guards';
const routes: Routes = [
  {
    path: '',
    component: BusinessLayoutComponent,
    canActivate: [isAuthenticatedGuard],
    children: [
      {
        path: 'inventary',
        canActivate: [isAuthenticatedGuard],
        component: InventarioComponent,
      },
      {
        path: 'category',
        canActivate: [isAuthenticatedGuard],
        component: CategoriasComponent,
      },
      {
        path: 'product',
        canActivate: [isAuthenticatedGuard],
        component: ProductosComponent,
      },
      {
        path: 'product-details/:id/:idCategory',
        canActivate: [isAuthenticatedGuard],
        component: ProductosDetailsComponent
      },
      {
        path: 'report',
        canActivate: [isAuthenticatedGuard],
        component: ReportesComponent,
      },
      {
        path: 'supplier',
        canActivate: [isAuthenticatedGuard],
        component: ProveedoresComponent
      },
      {
        path: 'transicion',
        canActivate: [isAuthenticatedGuard],
        component: TransicionComponent
      },
      {
        path: 'transaccion',
        canActivate: [isAuthenticatedGuard],
        component: TransaccionComponent
      },
      {
        path: '**',
        redirectTo: 'home'
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BusinessRoutingModule { }
