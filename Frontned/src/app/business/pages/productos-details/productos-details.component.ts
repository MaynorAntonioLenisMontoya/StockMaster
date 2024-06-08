import { Component, OnInit, inject } from '@angular/core';
import { Producto, ProductoForm, ProductsInventary, Result } from '../../interfaces/product';
import { ProductsService } from '../../services/products.service';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs';
import { ConfirmationService, MessageService } from 'primeng/api';

@Component({
  selector: 'app-productos-details',
  templateUrl: './productos-details.component.html',
  styleUrl: './productos-details.component.css'
})
export class ProductosDetailsComponent implements OnInit{

  public inventaryProductDialog: boolean = false;
  public inventaryProducts!: ProductsInventary[];
  public inventaryProduct!: ProductsInventary;
  public product!: ProductoForm;
  public selectedInventaryProducts!: ProductsInventary[] | null;
  public submitted: boolean = false;
  public isViewSelect: boolean = true;
  public nameModal: string = '';
  public pageNumber: number = 0;
  public pageSize: number = 5;
  public totalRecord: number = 10;
  public inventaryId?: number;
  public categoryId?: number;

  private productService = inject(ProductsService);
  private activatedRoute = inject(ActivatedRoute);
  private confirmationService= inject(ConfirmationService);
  private messageService= inject(MessageService);

  ngOnInit(): void {

    this.getProductInventary()
    this.dataSelected();

  }

  openNew() {
    this.nameModal = 'Agregar Producto'
    this.product = {
      id: 0,
      nombre: '',
      descripcion: '',
      precioUnitario: 0,
      cantidadDisponible: 0,
      nivelMinimoStock: 0,
    };
    this.submitted = false;
    this.inventaryProductDialog = true;
    this.isViewSelect = true;
  }

  getProductInventary() {
    this.activatedRoute.params
    .pipe(
      switchMap( ({ id }) => this.productService.getProductByInventary(id, this.pageSize, this.pageNumber)),
    ).subscribe( product => {
      this.totalRecord = product.paginator.totalItems;
      this.inventaryProducts  = product.result;

    })
  }

  onPageChange(event: any) {
    console.log({event});
    this.pageNumber = event.page;
    this.pageSize = event.rows;
    this.getProductInventary();
  }

  hideDialog() {
    this.inventaryProductDialog = false;
    this.submitted = false;
  }

  getOnNewProduct() {
    this.hideDialog();
    this.getProductInventary();
  }

  editProduct(data: Producto) {
    this.nameModal = 'Actualizar Producto'
    this.product = {
      id: data.id,
      nombre: data.nombre,
      descripcion: data.descripcion,
      precioUnitario: data.precioUnitario,
      cantidadDisponible: data.cantidadDisponible,
      nivelMinimoStock: data.nivelMinimoStock,
     };
    this.inventaryProductDialog = true;
    this.submitted = false;
    this.isViewSelect = false;
  }

  deleteProduct(data: Producto) {
    this.confirmationService.confirm({
      message: '¿Está seguro de que desea eliminar el producto seleccionado?',
      header: 'Confirme',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
          this.productService.deleteById(data.id)
          .subscribe((resp: Result) => {
            console.log(resp);
            this.messageService.add({ severity: 'success', summary: 'Successful', detail: resp.message, life: 3000 });
            this.selectedInventaryProducts = null;
            this.pageNumber = 0;
            this.getProductInventary();
          });
      }
  });
  }

  dataSelected() {
    this.activatedRoute.params
    .subscribe(param => {
      this.inventaryId = param['id'];
      this.categoryId = param['idCategory'];
    });
  }

}
