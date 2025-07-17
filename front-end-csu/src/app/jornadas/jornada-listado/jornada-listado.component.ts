import { Component, OnInit } from '@angular/core';
import { JornadaService } from '../jornada.service';
import { Jornada } from '../jornada.model';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-jornada-listado',
  standalone: true,
  templateUrl: './jornada-listado.html',
  styleUrls: ['./jornada-listado.scss'],
  imports: [
    CommonModule,
    RouterModule,
    MatTableModule,
    MatButtonModule
  ]
})
export class JornadaListadoComponent implements OnInit {
  jornadas: Jornada[] = [];

  constructor(private jornadaService: JornadaService, private router: Router) {}

  ngOnInit(): void {
    this.jornadaService.listar().subscribe(data => this.jornadas = data);
  }

  eliminar(id: number): void {
    if (confirm('¿Eliminar esta jornada?')) {
      this.jornadaService.eliminar(id).subscribe(() => {
        this.jornadas = this.jornadas.filter(j => j.id !== id);
      });
    }
  }
}

