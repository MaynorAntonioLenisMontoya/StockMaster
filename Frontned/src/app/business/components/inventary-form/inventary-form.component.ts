import { Component, EventEmitter, Input, OnChanges, Output, SimpleChanges, inject } from '@angular/core';
import { Inventary, Result } from '../../interfaces/inventary';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ValidatorsService } from '../../services/validators.service';
import { InventaryService } from '../../services/inventary.service';

@Component({
  selector: 'app-inventary-form',
  templateUrl: './inventary-form.component.html',
  styleUrl: './inventary-form.component.css'
})
export class InventaryFormComponent implements OnChanges{

  @Input()
  public inventary?: Inventary;

  @Input()
  public updateInventary?: Inventary;

  @Output()
  public onNewInventary: EventEmitter<Result> = new EventEmitter();

  public isViewInputId: boolean = false;

  private fb = inject( FormBuilder );
  private validatorService = inject(ValidatorsService);
  private inventaryService = inject(InventaryService);


  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes['updateInventary']);

    if (changes['updateInventary'] && changes['updateInventary'].currentValue) {
      this.myForm.patchValue(changes['updateInventary'].currentValue);
      if( this.myForm.controls['id'].value > 0 ) {
        this.isViewInputId = true;
      }
    } else {
      this.isViewInputId = false;
    }
  }

  public myForm: FormGroup = this.fb.group({
    id: [0],
    nombre: ['', Validators.required],
    descripcion: ['', Validators.required]
  });

  isValidField( field: string ) {
    return this.validatorService.isValidField(this.myForm, field);
  }

  getCurrentInventary(): Inventary {
    const inv = this.myForm.value as Inventary;

    return inv;
  }

  saveOrEditInventary(action: string) {
    if (action.includes('save')) {
      this.inventary = this.getCurrentInventary();
      this.saveInventary(this.inventary)
    } else {
      this.updateInventary = this.getCurrentInventary();
      this.modifyInventary(this.updateInventary)
    }
  }

  saveInventary(data: Inventary) {
    console.log('guardando inventario');
    this.inventaryService.saveInventary(data)
    .subscribe(( resp: Result ) => {
        console.log(resp);
        this.onNewInventary.emit(resp)
    });
  }

  modifyInventary(data: Inventary) {
    console.log('guardando inventario');
    this.inventaryService.updateInventary(data)
    .subscribe(( resp: Result ) => {
        console.log(resp);
        this.onNewInventary.emit(resp)
    });
  }

}
