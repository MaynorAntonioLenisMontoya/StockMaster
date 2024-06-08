import { Component, EventEmitter, Input, OnChanges, Output, SimpleChanges, inject } from '@angular/core';
import { Proveedor, Result } from '../../interfaces/proveedor';
import { ValidatorsService } from '../../services/validators.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProveedorService } from '../../services/proveedor.service';

@Component({
  selector: 'app-proveedor-form',
  templateUrl: './proveedor-form.component.html',
  styleUrl: './proveedor-form.component.css'
})
export class ProveedorFormComponent implements OnChanges{

  @Input()
  public proveedor?: Proveedor;

  @Input()
  public updateProveedor?: Proveedor;

  @Output()
  public onNewProveedor: EventEmitter<Result> = new EventEmitter();

  public isViewInputId: boolean = false;

  private fb = inject( FormBuilder );
  private validatorService = inject(ValidatorsService);
  private proveedorService = inject(ProveedorService);


  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes['updateProveedor']);

    if (changes['updateProveedor'] && changes['updateProveedor'].currentValue) {
      this.myForm.patchValue(changes['updateProveedor'].currentValue);
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
    email: ['', Validators.required],
    telefono: ['', Validators.required]
  });

  isValidField( field: string ) {
    return this.validatorService.isValidField(this.myForm, field);
  }

  getCurrentInventary(): Proveedor {
    const inv = this.myForm.value as Proveedor;

    return inv;
  }

  saveOrEditProveedor(action: string) {
    if (action.includes('save')) {
      this.proveedor = this.getCurrentInventary();
      this.saveProveedor(this.proveedor)
    } else {
      this.updateProveedor = this.getCurrentInventary();
      this.modifyProveedor(this.updateProveedor)
    }
  }

  saveProveedor(data: Proveedor) {
    console.log('guardando inventario');
    this.proveedorService.saveProveedor(data)
    .subscribe(( resp: Result ) => {
        console.log(resp);
        this.onNewProveedor.emit(resp)
    });
  }

  modifyProveedor(data: Proveedor) {
    console.log('guardando inventario');
    this.proveedorService.updateProveedor(data)
    .subscribe(( resp: Result ) => {
        console.log(resp);
        this.onNewProveedor.emit(resp)
    });
  }

}
