import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UsuarioService } from '../usuario.service';
import { Usuario } from '../usuario.model';

// Angular Material
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { MatCardModule } from '@angular/material/card';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-usuario-editar',
  standalone: true,
  templateUrl: './usuario-editar.html',
  styleUrl: './usuario-editar.scss',
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatOptionModule,
    MatCardModule
  ]
})
export class UsuarioEditarComponent implements OnInit {
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
    enabled: false
  };

  usuarioCargado = false;

  constructor(
    private usuarioService: UsuarioService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.usuarioService.obtener(+id).subscribe(data => {
        this.usuario = data;
        this.usuarioCargado = true; // Se activa cuando se termina de cargar
      });
    }
  }

  guardar(): void {
    if (!this.usuario.username || !this.usuario.password) {
      alert('Username y contraseña son obligatorios.');
      return;
    }

    this.usuarioService.actualizar(this.usuario).subscribe(() => {
      alert('Usuario actualizado con éxito.');
      this.router.navigate(['/usuarios']);
    });
  }

  cancelar(): void {
    this.router.navigate(['/usuarios']);
  }
}




