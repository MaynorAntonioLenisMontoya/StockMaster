import { Component, OnInit, inject } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Proveedor, Result } from '../../interfaces/proveedor';
import { ProveedorService } from '../../services/proveedor.service';

@Component({
  selector: 'app-proveedores',
  templateUrl: './proveedores.component.html',
  styleUrl: './proveedores.component.css'
})
export class ProveedoresComponent implements OnInit{

  public proveedorDialog: boolean = false;

  public proveedores!: Proveedor[];

  public proveedor!: Proveedor;

  public selectedProveedores!: Proveedor[] | null;

  public submitted: boolean = false;

  public statuses!: any[];

  public nameModal: string = '';

  public pageNumber: number = 0;

  public pageSize: number = 5;

  public totalRecord: number = 10;

  private confirmationService= inject(ConfirmationService);
  private messageService= inject(MessageService);
  private proveedorService= inject(ProveedorService);

  ngOnInit() {
    this.getProveedores();
  }


  openNew() {
    this.nameModal = 'Crear Proveedor'
    this.proveedor = {
      id: 0,
      nombre: '',
      email: '',
      telefono: ''
    };
    this.submitted = false;
    this.proveedorDialog = true;
  }

  getProveedores() {
    this.proveedorService.getProveedores(this.pageSize, this.pageNumber)
    .subscribe( (resp: Result) => {
      this.proveedores = resp.result;
      this.totalRecord = resp.paginator.totalItems;
    })
  }

  hideDialog() {
    this.proveedorDialog = false;
    this.submitted = false;
  }

  onPageChange(event: any) {
    this.pageNumber = event.page;
    this.pageSize = event.rows;
    this.getProveedores();
  }

  getOnNewProveedor() {
   this.hideDialog()
    this.getProveedores();
  }

  getSeverity(status: string) {
      switch (status) {
          case 'INSTOCK':
              return 'success';
          case 'LOWSTOCK':
              return 'warning';
          case 'OUTOFSTOCK':
              return 'danger';
      }
      return "";
  }

  editProveedor(data: Proveedor) {
    this.nameModal = 'Actualizar Proveedor'
      this.proveedor = { ...data };
      this.proveedorDialog = true;
      this.submitted = false;
  }

  deleteProveedor(data: Proveedor) {
    console.log('delete');
    console.log(data);
    this.confirmationService.confirm({
      message: '¿Está seguro de que desea eliminar el inventario seleccionado?',
      header: 'Confirme',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
          this.proveedorService.deleteById(data.id)
          .subscribe((resp: Result) => {
            console.log(resp);
            this.messageService.add({ severity: 'success', summary: 'Successful', detail: resp.message, life: 3000 });
            this.selectedProveedores = null;
            this.pageNumber = 0;
            this.getProveedores();
          });
      }
  });
  }

}
