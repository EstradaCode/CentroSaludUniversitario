import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CampaniaService } from '../campania.service';
import { Campania } from '../campania.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
// Angular Material
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { MatCardModule } from '@angular/material/card';
import { RouterModule } from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-campania-formulario',
  templateUrl: './campania-formulario.html',
  styleUrls: ['./campania-formulario.scss'],
  imports: [CommonModule,
    FormsModule,
    RouterModule,

    // Angular Material
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatOptionModule,
    MatCardModule], // Angular Material, FormsModule, etc.
})
export class CampaniaFormularioComponent implements OnInit {
 campania: Campania = {
  nombre: '',
  rutaArchivoEncuesta: '',
  fechaInicio: '',
  fechaFin: '',
  organizacionSocial: { id: 1 }, // por ahora hardcoded
  zona: [],
  jornadas: [],
  encuestadores: [],
};

  modoEdicion = false;

  constructor(
    private service: CampaniaService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.modoEdicion = true;
      this.service.obtener(+id).subscribe(data => this.campania = data);
    }
  }
  irAgregarJornada(): void {
    this.router.navigate(['/jornadas/nueva'], {
      state: { returnTo: '/campanias/nueva', jornadaActual: null }
    });
  }
  get fechasInvalidas(): boolean {
  if (!this.campania.fechaInicio || !this.campania.fechaFin) {
    return false; // no hay fechas cargadas aún, no se invalida
  }

  const inicio = new Date(this.campania.fechaInicio);
  const fin = new Date(this.campania.fechaFin);

  return fin < inicio;
}


  guardar(): void {
    const obs = this.modoEdicion
      ? this.service.actualizar(this.campania)
      : this.service.crear(this.campania);

    obs.subscribe(() => this.router.navigate(['/campanias']));
  }
}
