import { Component, OnInit, inject } from '@angular/core';
import { InventaryProduct, Producto, Result } from '../../interfaces/product';
import { ProductsService } from '../../services/products.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrl: './productos.component.css'
})
export class ProductosComponent implements OnInit{

  public inventaryProductDialog: boolean = false;
  public inventaryProducts!: InventaryProduct[];
  public inventaryProduct!: InventaryProduct;
  public product!: Producto;
  public selectedInventaryProducts!: InventaryProduct[] | null;
  public submitted: boolean = false;
  public nameModal: string = '';
  public pageNumber: number = 0;
  public pageSize: number = 5;
  public totalRecord: number = 10;

  private productService = inject(ProductsService);
  private router = inject(Router);

  ngOnInit(): void {
    this.getInventaryProducts();
  }

  openNew() {
    this.nameModal = 'Agregar Producto'
    this.product = {
      id: 0,
      nombre: '',
      descripcion: '',
      categoriaId: 0,
      precioUnitario: 0,
      cantidadDisponible: 0,
      nivelMinimoStock: 0,
      inventarioId: 0
    };
    this.submitted = false;
    this.inventaryProductDialog = true;
  }

  onPageChange(event: any) {
    console.log({event});
    this.pageNumber = event.page;
    this.pageSize = event.rows;
    this.getInventaryProducts();
  }

  getInventaryProducts() {
    this.productService.getInventaryProduct(this.pageSize, this.pageNumber)
    .subscribe( (resp: Result) => {
      this.totalRecord = resp.paginator.totalItems;
      this.inventaryProducts = resp.result;
    });
  }

  navegateProductDetails(id: number, categoriaId: number) {
    console.log(id);

    this.router.navigate(['/business/product-details', id, categoriaId]);
  }

  hideDialog() {
    this.inventaryProductDialog = false;
    this.submitted = false;
  }

  getOnNewProduct() {
    this.hideDialog();
    this.getInventaryProducts();
  }
}
