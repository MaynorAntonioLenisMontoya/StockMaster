import { Component, OnInit, inject } from '@angular/core';
import { Category, Result } from '../../interfaces/category';
import { CategoryService } from '../../services/category.service';
import { ConfirmationService, MessageService } from 'primeng/api';

@Component({
  selector: 'app-categorias',
  templateUrl: './categorias.component.html',
  styleUrl: './categorias.component.css'
})
export class CategoriasComponent implements OnInit{

  public categoryDialog: boolean = false;
  public submitted: boolean = false;
  public category!: Category;
  public nameModal: string = '';
  public categories!: Category[];
  public selectedCategories!: Category[] | null;
  public pageNumber: number = 0;
  public pageSize: number = 5;
  public totalRecord: number = 10;

  private categoryService = inject(CategoryService);
  private messageService= inject(MessageService);
  private confirmationService= inject(ConfirmationService);

  ngOnInit() {
    this.getCategories();
  }

  openNew() {
      this.nameModal = 'Crear Categoria'
      this.category = {
        id: 0,
        nombre: '',
        descripcion: ''
      };
      this.submitted = false;
      this.categoryDialog = true;
  }

  onPageChange(event: any) {
    console.log({event});
    this.pageNumber = event.page;
    this.pageSize = event.rows;
    this.getCategories();
  }

  editCategory(data: Category) {
    this.nameModal = 'Actualizar Categoria'
    this.category = { ...data };
    this.categoryDialog = true;
    this.submitted = false;
  }

  deleteCategory(data: Category) {
    this.confirmationService.confirm({
      message: '¿Está seguro de que desea eliminar la categoria seleccionada?',
      header: 'Confirme',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
          this.categoryService.deleteById(data.id)
          .subscribe((resp: Result) => {
            console.log(resp);
            this.messageService.add({ severity: 'success', summary: 'Successful', detail: resp.message, life: 3000 });
            this.selectedCategories = null;
            this.pageNumber = 0;
            this.getCategories();
          });
      }
  });
  }

  getCategories() {
    this.categoryService.getcategories(this.pageSize, this.pageNumber)
    .subscribe( ( resp:Result ) => {
      this.totalRecord = resp.paginator.totalItems;
      this.categories = resp.result;
    });
  }

  getOnNewCategory() {
    this.hideDialog();
    this.getCategories();
  }

  hideDialog() {
    this.categoryDialog = false;
    this.submitted = false;
    this.getCategories();
}

}
