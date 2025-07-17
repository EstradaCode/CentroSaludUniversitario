import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EncuestadorEditar } from './encuestador-editar.component';

describe('EncuestadorEditar', () => {
  let component: EncuestadorEditar;
  let fixture: ComponentFixture<EncuestadorEditar>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EncuestadorEditar]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EncuestadorEditar);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
