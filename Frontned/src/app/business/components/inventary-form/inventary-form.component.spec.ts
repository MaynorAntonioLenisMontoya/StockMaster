import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InventaryFormComponent } from './inventary-form.component';

describe('InventaryFormComponent', () => {
  let component: InventaryFormComponent;
  let fixture: ComponentFixture<InventaryFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [InventaryFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InventaryFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
