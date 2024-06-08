import { Component, OnInit, inject } from '@angular/core';
import { Result, TipoTransicion } from '../../interfaces/tipo-transicion';
import { ConfirmationService, MessageService } from 'primeng/api';
import { TipoTransicionService } from '../../services/tipo-transicion.service';

@Component({
  selector: 'app-transicion',
  templateUrl: './transicion.component.html',
  styleUrl: './transicion.component.css'
})
export class TransicionComponent implements OnInit{

  public tipoTransicionDialog: boolean = false;

  public tipoTransiciones!: TipoTransicion[];

  public tipoTransicion!: TipoTransicion;

  public selectedTipoTransiciones!: TipoTransicion[] | null;

  public submitted: boolean = false;

  public statuses!: any[];

  public nameModal: string = '';

  public pageNumber: number = 0;

  public pageSize: number = 5;

  public totalRecord: number = 10;

  private messageService= inject(MessageService);
  private confirmationService= inject(ConfirmationService);
  private tipoTransicionServie = inject(TipoTransicionService);

  ngOnInit(): void {
    this.getTipoTransicion();
  }

  openNew() {
    this.nameModal = 'Crear Tipo Transición'
    this.tipoTransicion = {
      id: 0,
      nombre: ''
    };
    this.submitted = false;
    this.tipoTransicionDialog = true;
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

  deleteTipoTransicion(data: TipoTransicion) {
    this.confirmationService.confirm({
      message: '¿Está seguro de que desea eliminar el registro seleccionado?',
      header: 'Confirme',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
          this.tipoTransicionServie.deleteById(data.id)
          .subscribe((resp: Result) => {
            console.log(resp);
            this.messageService.add({ severity: 'success', summary: 'Successful', detail: resp.message, life: 3000 });
            this.selectedTipoTransiciones = null;
            this.pageNumber = 0;
            this.getTipoTransicion();
          });
      }
    });
  }

  editTipoTransicion(data: TipoTransicion) {
    this.nameModal = 'Actualizar Tipo Transición'
      this.tipoTransicion = { ...data };
      this.tipoTransicionDialog = true;
      this.submitted = false;
  }

  getTipoTransicion() {
    this.tipoTransicionServie.getTipoTransicionPageable(this.pageSize, this.pageNumber)
    .subscribe( (resp: Result) => {
      this.tipoTransiciones = resp.result;
      this.totalRecord = resp.paginator.totalItems;
    })
  }

  onPageChange(event: any) {
    console.log({event});
    this.pageNumber = event.page;
    this.pageSize = event.rows;
    this.getTipoTransicion();
  }

  hideDialog() {
    this.tipoTransicionDialog = false;
    this.submitted = false;
}

  getOnNewTipoTransicion() {
    this.hideDialog();
     this.getTipoTransicion();
   }

}
