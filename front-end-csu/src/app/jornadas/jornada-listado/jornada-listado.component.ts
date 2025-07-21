import { Component, OnInit } from '@angular/core';
import { Jornada } from '../jornada.model';
import { JornadaService } from '../jornada.service';
import { CampaniaService } from '../../campañas/campania.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MaterialModule } from '../../material.module';
import { FormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';

@Component({
  standalone: true,
  selector: 'app-jornada-listado',
  templateUrl: './jornada-listado.html',
  styleUrls: ['./jornada-listado.scss'],
  imports: [CommonModule, RouterModule, MaterialModule, FormsModule,MatTableModule]
})
export class JornadaListadoComponent implements OnInit {
  jornadas: Jornada[] = [];
  filtro: string = '';
  campaniaNombres: { [id: number]: string } = {};

  constructor(
    private jornadaService: JornadaService,
    private campaniaService: CampaniaService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.jornadaService.listar().subscribe(jornadas => {
      this.jornadas = jornadas;
      const idsUnicos = Array.from(
        new Set(jornadas.map(j => j.campania?.id).filter(Boolean))
      );

      idsUnicos.forEach(id => {
        this.campaniaService.obtener(id).subscribe(c => {
          this.campaniaNombres[id] = c.nombre;
        });
      });
    });
  }

  getNombreCampania(id?: number): string {
    return id && this.campaniaNombres[id] ? this.campaniaNombres[id] : '...';
  }

  eliminar(id: number): void {
    if (confirm('¿Eliminar jornada?')) {
      this.jornadaService.eliminar(id).subscribe(() => {
        this.jornadas = this.jornadas.filter(j => j.id !== id);
      });
    }
  }
}


