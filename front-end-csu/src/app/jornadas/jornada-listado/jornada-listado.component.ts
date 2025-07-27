import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Jornada } from '../jornada.model';
import { JornadaService } from '../jornada.service';
import { CampaniaService } from '../../campañas/campania.service';
import { Router, NavigationEnd } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MaterialModule } from '../../material.module';
import { FormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { filter } from 'rxjs/operators';

@Component({
  standalone: true,
  selector: 'app-jornada-listado',
  templateUrl: './jornada-listado.html',
  styleUrls: ['./jornada-listado.scss'],
  imports: [CommonModule, RouterModule, MaterialModule, FormsModule, MatTableModule]
})
export class JornadaListadoComponent implements OnInit {
  jornadas: Jornada[] = [];
  filtro: string = '';
  campaniaNombres: { [id: number]: string } = {};

  constructor(
    private jornadaService: JornadaService,
    private campaniaService: CampaniaService,
    private router: Router,
    private cdr: ChangeDetectorRef // 🧠 inyectamos para detección de cambios
  ) {}

  ngOnInit(): void {
    this.cargarJornadas();

    // Escuchar navegación para recargar la tabla si se vuelve a esta vista
    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe(() => {
        this.cargarJornadas();
      });
  }

  cargarJornadas(): void {
    this.jornadaService.listar().subscribe(jornadas => {
      this.jornadas = jornadas;

      const idsUnicos = Array.from(
        new Set(jornadas.map(j => j.campania?.id).filter(Boolean))
      );

      idsUnicos.forEach(id => {
        if (id && !this.campaniaNombres[id]) {
          this.campaniaService.obtener(id).subscribe(c => {
            this.campaniaNombres[id] = c.nombre;
            this.cdr.detectChanges(); // ✅ actualiza visualmente la tabla
          });
        }
      });

      this.cdr.detectChanges(); // ✅ también al terminar de setear jornadas
    });
  }

  getNombreCampania(id?: number): string {
    return id && this.campaniaNombres[id] ? this.campaniaNombres[id] : '...';
  }

  eliminar(id: number): void {
    if (confirm('¿Eliminar jornada?')) {
      this.jornadaService.eliminar(id).subscribe(() => {
        this.jornadas = this.jornadas.filter(j => j.id !== id);
        this.cdr.detectChanges(); // actualiza tras eliminar
      });
    }
  }
}


