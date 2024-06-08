import { Component, EventEmitter, Input, OnChanges, Output, SimpleChanges, inject } from '@angular/core';
import { Result, TipoTransicion } from '../../interfaces/tipo-transicion';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ValidatorsService } from '../../services/validators.service';
import { TipoTransicionService } from '../../services/tipo-transicion.service';

@Component({
  selector: 'app-tipo-transicion-form',
  templateUrl: './tipo-transicion-form.component.html',
  styleUrl: './tipo-transicion-form.component.css'
})
export class TipoTransicionFormComponent implements OnChanges {

  @Input()
  public tipoTransicion?: TipoTransicion;

  @Input()
  public updateTipoTransicion?: TipoTransicion;

  @Output()
  public onNewTipoTransicion: EventEmitter<Result> = new EventEmitter();

  public isViewInputId: boolean = false;

  private fb = inject( FormBuilder );
  private validatorService = inject( ValidatorsService );
  private tipoTransicionServie = inject(TipoTransicionService);

  public myForm: FormGroup = this.fb.group({
    id: [0],
    nombre: ['', Validators.required],
  });

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['updateTipoTransicion'] && changes['updateTipoTransicion'].currentValue) {
      this.myForm.patchValue(changes['updateTipoTransicion'].currentValue);
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

  getCurrentTipoTransicion(): TipoTransicion {
    const inv = this.myForm.value as TipoTransicion;

    return inv;
  }

  saveOrEditTipoTransicion(type: string) {
    if (type.includes('save')) {
      this.tipoTransicion = this.getCurrentTipoTransicion();
      this.saveTipoTransicion(this.tipoTransicion);
    } else {
      this.tipoTransicion = this.getCurrentTipoTransicion();
      this.modifyTipoTransicion(this.tipoTransicion);
    }
  }

  saveTipoTransicion(data: TipoTransicion) {
    console.log('guardando inventario');
    this.tipoTransicionServie.saveTipoTransicion(data)
    .subscribe(( resp: Result ) => {
        console.log(resp);
        this.onNewTipoTransicion.emit(resp)
    });
  }

  modifyTipoTransicion(data: TipoTransicion) {
    console.log('guardando inventario');
    this.tipoTransicionServie.updateTipoTransicion(data)
    .subscribe(( resp: Result ) => {
        console.log(resp);
        this.onNewTipoTransicion.emit(resp)
    });
  }

}
