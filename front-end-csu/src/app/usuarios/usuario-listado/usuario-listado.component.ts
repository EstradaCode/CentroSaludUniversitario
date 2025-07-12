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

  constructor(private usuarioService: UsuarioService) {}

  ngOnInit(): void {
    this.usuarioService.listar().subscribe(data => this.usuarios = data);
  }

  eliminar(id: number) {
    this.usuarioService.eliminar(id).subscribe(() => {
      this.usuarios = this.usuarios.filter(u => u.id !== id);
    });
  }
}
