import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges, inject } from '@angular/core';
import { Producto, ProductoForm, Result } from '../../interfaces/product';
import { Result as CategoryResult } from '../../interfaces/category';
import { Inventary, Result as InventaryResult } from '../../interfaces/inventary';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ValidatorsService } from '../../services/validators.service';
import { ProductsService } from '../../services/products.service';
import { CategoryService } from '../../services/category.service';
import { Category } from '../../interfaces/category';
import { InventaryService } from '../../services/inventary.service';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrl: './product-form.component.css'
})
export class ProductFormComponent implements OnInit, OnChanges{

  ngOnInit(): void {
    this.getCategories();
    this.getInventary();
  }


  @Input()
  public product?: Producto;

  @Input()
  public updateProducto?: ProductoForm;

  @Input()
  public inventarySelected?: number;

  @Input()
  public categorySelected?: number;

  @Output()
  public onNewProducto: EventEmitter<Result> = new EventEmitter();

  public isViewInputId: boolean = false;

  @Input()
  public isViewSelect?: boolean;

  public categorias!: Category[];
  public inventary!: Inventary[];

  private fb = inject( FormBuilder );
  private validatorService = inject(ValidatorsService);
  private productoService = inject(ProductsService);
  private categoriaService = inject(CategoryService);
  private inventarioService = inject(InventaryService);


  public myForm: FormGroup = this.fb.group({
    id: [0],
    nombre: ['', Validators.required],
    descripcion: ['', Validators.required],
    categoriaId: ['', Validators.required],
    precioUnitario: [, Validators.required],
    cantidadDisponible: [, Validators.required],
    nivelMinimoStock: [, Validators.required],
    inventarioId: ['', Validators.required]
  });

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);

    if (changes['updateProducto'] && changes['updateProducto'].currentValue) {
      this.myForm.patchValue(changes['updateProducto'].currentValue);
      if( this.myForm.controls['id'].value > 0 ) {
        this.isViewInputId = true;
      }
    } else {
      this.isViewInputId = false;
    }
  }
  getCurrentProduct(): ProductoForm {
    const product = this.myForm.value as ProductoForm;

    return product;
  }

  isValidField( field: string ) {
    return this.validatorService.isValidField(this.myForm, field);
  }

  saveOrEditProduct(action: string) {
    if (action.includes('save')) {
      const data = this.getCurrentProduct();
      this.saveProduct(this.setProduct(data));
    } else {
      const data = this.getCurrentProduct();
      const product = this.setProduct(data)

      if (this.categorySelected !== undefined) {
        product.categoriaId = this.categorySelected;
      }

      if (this.inventarySelected !== undefined) {
        product.inventarioId = this.inventarySelected;
      }
      this.updateProduct(product);
    }
  }

  getCategories() {
    this.categoriaService.findAll()
    .subscribe( (resp: CategoryResult) => {
      this.categorias = resp.result;
    })
  }

  getInventary() {
    this.inventarioService.getFindAll()
    .subscribe( (resp:InventaryResult) => {
      this.inventary = resp.result;
    })
  }

  saveProduct(data: Producto) {
    this.productoService.saveProduct(data)
    .subscribe( (resp: Result) => {
      this.onNewProducto.emit(resp);
    });
  }

  updateProduct(data: Producto) {
    this.productoService.updateProduct(data)
    .subscribe( (resp: Result) => {
      this.onNewProducto.emit(resp);
    });
  }

  setProduct(data: ProductoForm): Producto {
    console.log(data);

    return this.product = {
      id: data.id == 0 ? 0 : data.id,
      nombre: data.nombre,
      descripcion: data.descripcion,
      categoriaId: data.categoriaId!.id,
      precioUnitario: data.precioUnitario,
      cantidadDisponible: data.cantidadDisponible,
      nivelMinimoStock: data.nivelMinimoStock,
      inventarioId: data.inventarioId!.id
    }

  }


}
