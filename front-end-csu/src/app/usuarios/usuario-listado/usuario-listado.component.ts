import { Component, OnInit } from '@angular/core';
import { Usuario } from '../usuario.model';
import { UsuarioService } from '../usuario.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
@Component({
  selector: 'app-usuario-listado',
  standalone: true,
  imports: [CommonModule,RouterModule], 
  templateUrl: './usuario-listado.html',
})
export class UsuarioListadoComponent implements OnInit {
  usuarios: Usuario[] = [];
usuarioEnEdicion: Usuario | null = null;

  constructor(private usuarioService: UsuarioService) {}

  ngOnInit(): void {
    this.obtenerUsuarios();
  }
  obtenerUsuarios(): void {
  this.usuarioService.listar().subscribe(data => this.usuarios = data);
}

 eliminarUsuario(id: number): void {
  if (confirm('¿Estás seguro que querés eliminar este usuario?')) {
    this.usuarioService.eliminar(id).subscribe(() => {
      this.usuarios = this.usuarios.filter(u => u.id !== id);
    });
  }
}
editarUsuario(usuario: Usuario): void {
  this.usuarioEnEdicion = { ...usuario }; // Clonamos para no modificar directo
}

guardarCambios(): void {
  if (this.usuarioEnEdicion && this.usuarioEnEdicion.id != null) {
    this.usuarioService.actualizar(this.usuarioEnEdicion).subscribe(() => {
      this.obtenerUsuarios(); // Refrescar datos
      this.usuarioEnEdicion = null; // Salir del modo edición
    });
  }
}

cancelarEdicion(): void {
  this.usuarioEnEdicion = null;
}


}

