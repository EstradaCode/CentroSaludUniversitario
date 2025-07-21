import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CampaniaService } from '../campania.service';
import { Campania } from '../campania.model';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-campania-editar',
  standalone: true,
  templateUrl: './campania-editar.html',
  styleUrls: ['./campania-editar.scss'],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ]
})
export class CampaniaEditarComponent implements OnInit {
  campania: Campania = {
    nombre: '',
    rutaArchivoEncuesta: '',
    fechaInicio: '',
    fechaFin: '',
    organizacionSocial: { id: 1 },
    zona: [],
    jornadas: [],
    encuestadores: []
  };

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private campaniaService: CampaniaService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.campaniaService.obtener(+id).subscribe(data => {
        this.campania = data;
      });
    }
  }

  guardar(): void {
    this.campaniaService.actualizar(this.campania).subscribe(() => {
      alert('Campaña actualizada con éxito.');
      this.router.navigate(['/campanias']);
    });
  }

  cancelar(): void {
    this.router.navigate(['/campanias']);
  }

  irAgregarBarrio(): void {
    this.router.navigate(['/barrios/asociar'], {
      queryParams: { campaniaId: this.campania.id }
    });
  }

  irAgregarEncuestador(): void {
    this.router.navigate(['/encuestadores/nuevo'], {
      queryParams: { campaniaId: this.campania.id }
    });
  }

  irAgregarJornada(): void {
    this.router.navigate(['/jornadas/nueva'], {
      queryParams: { campaniaId: this.campania.id }
    });
  }
}
