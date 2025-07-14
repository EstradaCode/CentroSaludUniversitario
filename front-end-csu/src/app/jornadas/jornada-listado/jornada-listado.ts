import { Component, OnInit } from '@angular/core';
import { JornadaService } from '../jornada.service';
import { Jornada } from '../jornada.model';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';

@Component({
  selector: 'app-jornada-listado',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './jornada-listado.html',
})
export class JornadaListadoComponent implements OnInit {
  jornadas: Jornada[] = [];

  constructor(private jornadaService: JornadaService, private router: Router) {}

  ngOnInit(): void {
    this.obtenerJornadas();
  }

  obtenerJornadas(): void {
    this.jornadaService.listar().subscribe(data => this.jornadas = data);
  }

  eliminarJornada(id: number): void {
    if (confirm('¿Eliminar esta jornada?')) {
      this.jornadaService.eliminar(id).subscribe(() => {
        this.jornadas = this.jornadas.filter(j => j.id !== id);
      });
    }
  }

  editarJornada(id: number): void {
    this.router.navigate(['/jornadas/editar', id]);
  }
}
