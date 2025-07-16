import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UsuarioService } from '../usuario.service';
import { Usuario } from '../usuario.model';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';

@Component({
  selector: 'app-usuario-editar',
  standalone: true,
  templateUrl: './usuario-editar.html',
  styleUrls: ['./usuario-editar.scss'],
  imports: [
  CommonModule,
  FormsModule,
  MatFormFieldModule,
  MatInputModule,
  MatButtonModule,
  MatSelectModule,
  MatOptionModule,
  RouterModule
],
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
    enabled: false,
  };

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private usuarioService: UsuarioService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.usuarioService.obtener(+id).subscribe((data) => {
        this.usuario = data;
      });
    }
  }

  guardar(): void {
    this.usuarioService.actualizar(this.usuario).subscribe(() => {
      alert('Usuario actualizado con éxito.');
      this.router.navigate(['/usuarios']);
    });
  }
  cancelar(): void {
  this.router.navigate(['/usuarios']);
}
}

