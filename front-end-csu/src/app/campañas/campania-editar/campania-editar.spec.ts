import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CampaniaEditarComponent } from './campania-editar.component';

describe('CampaniaEditar', () => {
  let component: CampaniaEditarComponent;
  let fixture: ComponentFixture<CampaniaEditarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CampaniaEditarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CampaniaEditarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
