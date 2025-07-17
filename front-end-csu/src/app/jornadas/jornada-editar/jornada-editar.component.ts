import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { JornadaService } from '../jornada.service';
import { Jornada } from '../jornada.model';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-jornada-editar',
  standalone: true,
  templateUrl: './jornada-editar.html',
  styleUrls: ['./jornada-editar.scss'],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ]
})
export class JornadaEditarComponent implements OnInit {
  jornada: Jornada = {
    fecha: '',
    horaInicio: '',
    horaFin: '',
    campania: { id: 0 }
  };

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private jornadaService: JornadaService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.jornadaService.obtener(+id).subscribe(data => {
        this.jornada = data;
      });
    }
  }

  guardar(): void {
    this.jornadaService.actualizar(this.jornada).subscribe(() => {
      alert('Jornada actualizada.');
      this.router.navigate(['/jornadas']);
    });
  }

  cancelar(): void {
    this.router.navigate(['/jornadas']);
  }
}
