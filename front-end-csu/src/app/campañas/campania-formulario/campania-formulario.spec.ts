import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CampaniaFormularioComponent } from './campania-formulario.component';

describe('CampaniaFormulario', () => {
  let component: CampaniaFormularioComponent;
  let fixture: ComponentFixture<CampaniaFormularioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CampaniaFormularioComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CampaniaFormularioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
