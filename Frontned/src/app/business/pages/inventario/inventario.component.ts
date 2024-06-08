import { Component, OnInit, inject } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Inventary, Result } from '../../interfaces/inventary';
import { InventaryService } from '../../services/inventary.service';

@Component({
  selector: 'app-inventario',
  templateUrl: './inventario.component.html',
  styleUrl: './inventario.component.css'
})
export class InventarioComponent implements OnInit{

  public inventaryDialog: boolean = false;

  public inventarys!: Inventary[];

  public inventary!: Inventary;

  public selectedInventary!: Inventary[] | null;

  public submitted: boolean = false;

  public statuses!: any[];

  public nameModal: string = '';

  public pageNumber: number = 0;

  public pageSize: number = 5;

  public totalRecord: number = 10;

  private inventaryService = inject(InventaryService);
  private messageService= inject(MessageService);
  private confirmationService= inject(ConfirmationService);


  ngOnInit() {
    this.getInventary();
  }

  openNew() {
      this.nameModal = 'Crear Inventario'
      this.inventary = {
        id: 0,
        nombre: '',
        descripcion: ''
      };
      this.submitted = false;
      this.inventaryDialog = true;
  }

  editInventary(data: Inventary) {
    this.nameModal = 'Actualizar Inventario'
      this.inventary = { ...data };
      this.inventaryDialog = true;
      this.submitted = false;
  }

  deleteInventary(data: Inventary) {
    console.log('delete');
    console.log(data);
    this.confirmationService.confirm({
      message: '¿Está seguro de que desea eliminar el inventario seleccionado?',
      header: 'Confirme',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
          this.inventaryService.deleteInventaryById(data.id)
          .subscribe((resp: Result) => {
            console.log(resp);
            this.messageService.add({ severity: 'success', summary: 'Successful', detail: resp.message, life: 3000 });
            this.selectedInventary = null;
            this.pageNumber = 0;
            this.getInventary();
          });
      }
  });
  }

  hideDialog() {
      this.inventaryDialog = false;
      this.submitted = false;
  }

  getInventary() {
    console.log('llamando servicio');
    this.inventaryService.getInventaryPageable(this.pageSize, this.pageNumber)
    .subscribe((resp: Result) => {
      console.log(resp);
      this.totalRecord = resp.paginator.totalItems;
      this.inventarys = resp.result;
    });
  }

  onPageChange(event: any) {
    console.log({event});

    this.pageNumber = event.page;
    this.pageSize = event.rows;
    this.getInventary();
  }

  getOnNewInventary() {
   this.hideDialog()
    this.getInventary();
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

}
