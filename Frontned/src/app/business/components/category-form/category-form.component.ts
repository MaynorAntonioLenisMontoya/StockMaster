import { Component, EventEmitter, Input, OnChanges, Output, SimpleChanges, inject } from '@angular/core';
import { Category, Result } from '../../interfaces/category';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ValidatorsService } from '../../services/validators.service';
import { CategoryService } from '../../services/category.service';

@Component({
  selector: 'app-category-form',
  templateUrl: './category-form.component.html',
  styleUrl: './category-form.component.css'
})
export class CategoryFormComponent implements OnChanges{

  @Input()
  public category?: Category;

  @Input()
  public updateCategory?: Category;

  @Output()
  public onNewCategory: EventEmitter<Result> = new EventEmitter();

  public isViewInputId: boolean = false;

  private fb = inject( FormBuilder );
  private validatorService = inject(ValidatorsService);
  private categoryService = inject(CategoryService);

  public myForm: FormGroup = this.fb.group({
    id: [0],
    nombre: ['', Validators.required],
    descripcion: ['', Validators.required]
  });

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['updateCategory'] && changes['updateCategory'].currentValue) {
      this.myForm.patchValue(changes['updateCategory'].currentValue);
      if( this.myForm.controls['id'].value > 0 ) {
        this.isViewInputId = true;
      }
    } else {
      this.isViewInputId = false;
    }
  }

  isValidField( field: string ) {
    return this.validatorService.isValidField(this.myForm, field);
  }

  getCurrentCategory(): Category {
    const cate = this.myForm.value as Category;

    return cate;
  }

  saveOrEditCategory(action: string) {
    if (action.includes('save')) {
      this.category = this.getCurrentCategory();
      this.saveCategory(this.category)
    } else {
      this.updateCategory = this.getCurrentCategory();
      this.modifyCategory(this.updateCategory)
    }
  }

  saveCategory(data: Category) {
    console.log('guardando categoria');
    this.categoryService.saveCategory(data)
    .subscribe(( resp: Result ) => {
        console.log(resp);
        this.onNewCategory.emit(resp)
    });
  }

  modifyCategory(data: Category) {
    console.log('actualizando categoria');
    this.categoryService.updateCategory(data)
    .subscribe(( resp: Result ) => {
        console.log(resp);
        this.onNewCategory.emit(resp)
    });
  }


}
