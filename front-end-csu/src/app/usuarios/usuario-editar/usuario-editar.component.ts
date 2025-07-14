import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UsuarioService } from '../usuario.service';
import { Usuario } from '../usuario.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-usuario-editar',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './usuario-editar.html'
})
export class UsuarioEditarComponent implements OnInit {
  usuario: Usuario = {} as Usuario;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private usuarioService: UsuarioService
  ) {}

 ngOnInit(): void {
  console.log('ngOnInit ejecutado');
  this.route.paramMap.subscribe(params => {
    const id = Number(params.get('id'));
    console.log('ID recibido:', id);
    this.usuarioService.obtener(id).subscribe(data => {
      console.log('Usuario cargado:', data);
      this.usuario = data;
    });
  });
}


  guardar(): void {
    this.usuarioService.actualizar(this.usuario).subscribe(() => {
      alert('Usuario actualizado correctamente');
      this.router.navigate(['/usuarios']);
    });
  }

  cancelar(): void {
    this.router.navigate(['/usuarios']);
  }
}
