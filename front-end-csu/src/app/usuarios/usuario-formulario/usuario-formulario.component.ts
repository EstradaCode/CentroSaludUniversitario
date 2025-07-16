import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UsuarioService } from '../usuario.service';
import { Usuario } from '../usuario.model';
import { Router } from '@angular/router';

// Angular Material
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { MatCardModule } from '@angular/material/card';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-usuario-formulario',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,

    // Angular Material
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatOptionModule,
    MatCardModule
  ],
  templateUrl: './usuario-formulario.html',
  styleUrl: './usuario-formulario.scss'
})
export class UsuarioFormularioComponent {
  usuario: Usuario = {
    username: '',
    nombre: '',
    apellido: '',
    email: '',
    telefono: 0,
    dni: 0,
    matricula: 0,
    password: '',
    rol: 'VISITANTE',
    enabled: false,
  };

  constructor(private usuarioService: UsuarioService, private router: Router) {}

  guardar(): void {
    if (!this.usuario.username || !this.usuario.password) {
      alert('Username y contraseña son obligatorios.');
      return;
    }

    this.usuarioService.crear(this.usuario).subscribe(() => {
      alert('Usuario creado con éxito.');
      this.router.navigate(['/usuarios']);
    });
  }
}

