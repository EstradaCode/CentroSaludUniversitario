import { Component, OnInit } from '@angular/core';
import { CampaniaService } from '../campania.service';
import { Campania } from '../campania.model';
import { Router } from '@angular/router';
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

@Component({
  standalone: true,
  selector: 'app-campania-listado',
  templateUrl: './campania-listado.html',
  styleUrls: ['./campania-listado.scss'],
  imports: [CommonModule,RouterModule,MatSidenavModule,
  MatToolbarModule,
  MatListModule,
  MatButtonModule,
  MatInputModule,
  MatTableModule,FormsModule,
  MatFormFieldModule,MaterialModule], // agregá MaterialModules necesarios
})
export class CampaniaListadoComponent implements OnInit {
  campanias: Campania[] = [];
  campaniasFiltradas: Campania[] = [];
  filtro: string = '';

  constructor(private campaniaService: CampaniaService, private router: Router) {}

  ngOnInit(): void {
    this.campaniaService.listar().subscribe(data => {
      this.campanias = data;
      this.campaniasFiltradas = [...data];
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
      });
    }
  }
}


