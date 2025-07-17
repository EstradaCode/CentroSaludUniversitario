import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { EncuestadorService } from '../encuestador.service';
import { Encuestador } from '../encuestador.model';

@Component({
  selector: 'app-encuestador-formulario',
  standalone: true,
  templateUrl: './encuestador-formulario.html',
  styleUrls: ['./encuestador-formulario.scss'],
  imports: [
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ]
})
export class EncuestadorFormularioComponent {
  encuestador: Encuestador = {
    nombre: '',
    apellido: '',
    dni: 0,
    telefono: 0,
    horasTrabajadas: 0,
    barrio: { id: 0 }
  };

  constructor(private service: EncuestadorService, private router: Router) {}

  guardar(): void {
    this.service.crear(this.encuestador).subscribe(() => {
      this.router.navigate(['/encuestadores']);
    });
  }

  cancelar(): void {
    this.router.navigate(['/encuestadores']);
  }
}
