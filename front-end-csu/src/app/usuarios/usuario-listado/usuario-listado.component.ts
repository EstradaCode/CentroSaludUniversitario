import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Usuario } from '../usuario.model';
import { UsuarioService } from '../usuario.service';
import { CommonModule } from '@angular/common';
import { Router, RouterModule, NavigationEnd } from '@angular/router';
import { MaterialModule } from '../../material.module';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-usuario-listado',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    MatSidenavModule,
    MatToolbarModule,
    MatListModule,
    MatButtonModule,
    MatInputModule,
    MatTableModule,
    FormsModule,
    MatFormFieldModule,
    MaterialModule
  ],
  templateUrl: './usuario-listado.html',
})
export class UsuarioListadoComponent implements OnInit {
  usuarios: Usuario[] = [];
  usuariosFiltrados: Usuario[] = [];
  filtro: string = '';
  usuarioEnEdicion: Usuario | null = null;

  constructor(
    private usuarioService: UsuarioService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.obtenerUsuarios();

    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe(() => {
        this.obtenerUsuarios();
      });
  }

  obtenerUsuarios(): void {
    this.usuarioService.listar().subscribe(data => {
      this.usuarios = data;
      this.aplicarFiltro();
      this.cdr.detectChanges(); // 🧠 fuerza la actualización visual
    });
  }

  aplicarFiltro(): void {
    const filtroLower = this.filtro.toLowerCase();
    this.usuariosFiltrados = this.usuarios.filter(usuario =>
      usuario.username.toLowerCase().includes(filtroLower) ||
      usuario.email.toLowerCase().includes(filtroLower)
    );
  }

  eliminarUsuario(id: number): void {
    if (confirm('¿Estás seguro que querés eliminar este usuario?')) {
      this.usuarioService.eliminar(id).subscribe(() => {
        this.usuarios = this.usuarios.filter(u => u.id !== id);
        this.aplicarFiltro();
        this.cdr.detectChanges();
      });
    }
  }

  editarUsuario(usuario: Usuario): void {
    this.usuarioEnEdicion = { ...usuario };
  }

  guardarCambios(): void {
    if (this.usuarioEnEdicion && this.usuarioEnEdicion.id != null) {
      this.usuarioService.actualizar(this.usuarioEnEdicion).subscribe(() => {
        this.obtenerUsuarios();
        this.usuarioEnEdicion = null;
      });
    }
  }

  cancelarEdicion(): void {
    this.usuarioEnEdicion = null;
  }
}
