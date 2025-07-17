import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EncuestadorFormulario } from './encuestador-formulario.component';

describe('EncuestadorFormulario', () => {
  let component: EncuestadorFormulario;
  let fixture: ComponentFixture<EncuestadorFormulario>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EncuestadorFormulario]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EncuestadorFormulario);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
