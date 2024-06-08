import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BusinessRoutingModule } from './business-routing.module';
import { InventarioComponent } from './pages/inventario/inventario.component';
import { ProductosComponent } from './pages/productos/productos.component';
import { ProveedoresComponent } from './pages/proveedores/proveedores.component';
import { CategoriasComponent } from './pages/categorias/categorias.component';
import { ReportesComponent } from './pages/reportes/reportes.component';
import { PrimeNgModule } from '../primeng/primeng.module';
import { ReactiveFormsModule } from '@angular/forms';
import { ConfirmationService, MessageService } from 'primeng/api';
import { HttpClientModule } from '@angular/common/http';
import { InventaryFormComponent } from './components/inventary-form/inventary-form.component';
import { CategoryFormComponent } from './components/category-form/category-form.component';
import { ProductosDetailsComponent } from './pages/productos-details/productos-details.component';
import { ProductFormComponent } from './components/product-form/product-form.component';
import { TransicionComponent } from './pages/transicion/transicion.component';
import { TransaccionComponent } from './pages/transaccion/transaccion.component';
import { TipoTransicionFormComponent } from './components/tipo-transicion-form/tipo-transicion-form.component';
import { ProveedorFormComponent } from './components/proveedor-form/proveedor-form.component';
import { BusinessLayoutComponent } from './layouts/business-layout/business-layout.component';
import { DashboardModule } from '../dashboard/dashboard.module';


@NgModule({
  declarations: [
    InventarioComponent,
    ProductosComponent,
    ProveedoresComponent,
    CategoriasComponent,
    ReportesComponent,
    InventaryFormComponent,
    CategoryFormComponent,
    ProductosDetailsComponent,
    ProductFormComponent,
    TransicionComponent,
    TransaccionComponent,
    TipoTransicionFormComponent,
    ProveedorFormComponent,
    BusinessLayoutComponent
  ],
  providers: [MessageService, ConfirmationService],
  imports: [
    CommonModule,
    BusinessRoutingModule,
    PrimeNgModule,
    ReactiveFormsModule,
    HttpClientModule,
    DashboardModule
  ]
})
export class BusinessModule { }
