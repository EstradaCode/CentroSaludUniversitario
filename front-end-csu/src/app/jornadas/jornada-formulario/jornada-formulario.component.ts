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
  selector: 'app-jornada-formulario',
  standalone: true,
  templateUrl: './jornada-formulario.html',
  styleUrls: ['./jornada-formulario.scss'],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ]
})
export class JornadaFormularioComponent implements OnInit {
  jornada: Jornada = {
    fecha: '',
    horaInicio: '',
    horaFin: '',
    campania: { id: 0 }
  };

  constructor(
    private jornadaService: JornadaService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const campaniaId = +params['campaniaId'];
      if (campaniaId) this.jornada.campania.id = campaniaId;
    });
  }

  guardar(): void {
    this.jornadaService.crear(this.jornada).subscribe(() => {
      alert('Jornada creada');
      this.router.navigate(['/campanias']); // o a donde decidas volver
    });
  }

  cancelar(): void {
    this.router.navigate(['/campanias']);
  }
}
