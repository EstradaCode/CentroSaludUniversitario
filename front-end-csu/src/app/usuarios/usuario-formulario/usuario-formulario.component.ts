import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UsuarioService } from '../usuario.service';
import { Usuario } from '../usuario.model';
import { Router } from '@angular/router';
@Component({
  selector: 'app-usuario-formulario',
  imports: [FormsModule],
  templateUrl: './usuario-formulario.html',
  styleUrl: './usuario-formulario.scss'
})
export class UsuarioFormularioComponent {
  usuario: Usuario = {
    id: 0,
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

  onSubmit(): void {
    console.log('Enviando usuario:', this.usuario); // debug
    this.usuarioService.crear(this.usuario).subscribe(() => {
      alert('Usuario creado con éxito.');
      this.router.navigate(['/usuarios']);
    });
  }
}

