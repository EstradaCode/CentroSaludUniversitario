import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EncuestadorEditarComponent } from './encuestador-editar.component';

describe('EncuestadorEditar', () => {
  let component: EncuestadorEditarComponent;
  let fixture: ComponentFixture<EncuestadorEditarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EncuestadorEditarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EncuestadorEditarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
