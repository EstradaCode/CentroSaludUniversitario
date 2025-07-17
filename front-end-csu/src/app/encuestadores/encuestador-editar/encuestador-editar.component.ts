import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { EncuestadorService } from '../encuestador.service';
import { Encuestador } from '../encuestador.model';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-encuestador-editar',
  standalone: true,
  templateUrl: './encuestador-editar.html',
  styleUrls: ['./encuestador-editar.scss'],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ]
})
export class EncuestadorEditarComponent implements OnInit {
  encuestador: Encuestador = {
    nombre: '',
    apellido: '',
    dni: 0,
    telefono: 0,
    horasTrabajadas: 0,
    barrio: { id: 0 }
  };

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: EncuestadorService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.service.obtener(+id).subscribe(data => this.encuestador = data);
    }
  }

  guardar(): void {
    this.service.actualizar(this.encuestador).subscribe(() => {
      alert('Encuestador actualizado');
      this.router.navigate(['/encuestadores']);
    });
  }

  cancelar(): void {
    this.router.navigate(['/encuestadores']);
  }
}
