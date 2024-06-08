import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-card',
  templateUrl: './home-card.component.html',
  styleUrl: './home-card.component.css'
})
export class HomeCardComponent {

  private route = inject(Router);

  public homeCard = [
    {
      title: 'Registro de productos',
      description: 'Facilita la gestión de inventarios con nuestra aplicación, donde puedes agregar fácilmente nuevos productos con detalles completos como nombre, descripción, categoría, precio unitario, proveedor y número de serie si es necesario.',
      img: '../../../../assets/Img/Registro_producto.jpeg',
      severity: 'secondary',
      route: '/business/product'
    },
    {
      title: 'Proveedores',
      description: 'Optimiza la gestión de proveedores con nuestra aplicación, que incluye una funcionalidad para gestionar información sobre los proveedores, como datos de contacto, historial de pedidos, precios acordados, etc.',
      img: '../../../../assets/Img/Proveedores.jpeg',
      severity: 'warning',
      route: '/business/supplier'
    },
    // {
    //   title: 'Reportes',
    //   description: 'Facilita el control del inventario con nuestra aplicación, que permite a los usuarios generar informes personalizados sobre el estado del inventario. Descubre los productos más vendidos, identifica los artículos con mayor rotación etc.',
    //   img: '../../../../assets/Img/Reportes.jpeg',
    //   severity: 'help'
    // }
  ];

  mapSeverity(severity: string): "secondary" | "warning" | "help" | "success" | "info" | "danger" | "primary" | "contrast" | null | undefined {
    // Aquí puedes implementar la lógica para mapear la cadena de severidad a un valor compatible
    // Por ejemplo, podrías hacer un mapeo manual o utilizar una lógica más compleja según tus necesidades
    switch(severity) {
      case "secondary":
        return "secondary";
      case "warning":
        return "warning";
      // Agrega otros casos según necesites
      default:
        return null; // O cualquier otro valor por defecto que necesites
    }
  }

  navegateByUrl(url: string) {
    this.route.navigate([url]);
  }
}
