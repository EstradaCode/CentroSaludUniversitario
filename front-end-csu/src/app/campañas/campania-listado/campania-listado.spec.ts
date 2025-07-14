import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CampaniaListadoComponent } from './campania-listado.component';

describe('CampaniaListado', () => {
  let component: CampaniaListadoComponent;
  let fixture: ComponentFixture<CampaniaListadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CampaniaListadoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CampaniaListadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
