import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CampaniaService } from '../campania.service';
import { Campania } from '../campania.model';
import { Router, NavigationEnd } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
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
  standalone: true,
  selector: 'app-campania-listado',
  templateUrl: './campania-listado.html',
  styleUrls: ['./campania-listado.scss'],
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
  ]
})
export class CampaniaListadoComponent implements OnInit {
  campanias: Campania[] = [];
  campaniasFiltradas: Campania[] = [];
  filtro: string = '';

  constructor(
    private campaniaService: CampaniaService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.cargarCampanias();

    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe(() => {
        this.cargarCampanias();
      });
  }

  cargarCampanias(): void {
    this.campaniaService.listar().subscribe(data => {
      this.campanias = data;
      this.aplicarFiltro();
      this.cdr.detectChanges(); // 👈 fuerza la actualización visual
    });
  }

  aplicarFiltro(): void {
    const f = this.filtro.toLowerCase();
    this.campaniasFiltradas = this.campanias.filter(c =>
      c.nombre.toLowerCase().includes(f)
    );
  }

  eliminarCampania(id: number): void {
    if (confirm('¿Eliminar campaña?')) {
      this.campaniaService.eliminar(id).subscribe(() => {
        this.campanias = this.campanias.filter(c => c.id !== id);
        this.aplicarFiltro();
        this.cdr.detectChanges(); // 👈 actualiza tras eliminar
      });
    }
  }
}



