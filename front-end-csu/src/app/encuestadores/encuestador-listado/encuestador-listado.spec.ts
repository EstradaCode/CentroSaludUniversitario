import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EncuestadorListado } from './encuestador-listado.component';

describe('EncuestadorListado', () => {
  let component: EncuestadorListado;
  let fixture: ComponentFixture<EncuestadorListado>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EncuestadorListado]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EncuestadorListado);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
