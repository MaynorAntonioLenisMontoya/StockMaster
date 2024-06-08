import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TipoTransicionFormComponent } from './tipo-transicion-form.component';

describe('TipoTransicionFormComponent', () => {
  let component: TipoTransicionFormComponent;
  let fixture: ComponentFixture<TipoTransicionFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TipoTransicionFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TipoTransicionFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
