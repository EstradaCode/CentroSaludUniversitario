import { Component, OnInit } from '@angular/core';
import { CampaniaService } from '../campania.service';
import { Campania } from '../campania.model';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';

@Component({
  selector: 'app-campania-listado',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './campania-listado.html'
})
export class CampaniaListadoComponent implements OnInit {
  campanias: Campania[] = [];

  constructor(private campaniaService: CampaniaService, private router: Router) {}

  ngOnInit(): void {
    this.obtenerCampanias();
  }

  obtenerCampanias(): void {
    this.campaniaService.listar().subscribe(data => this.campanias = data);
  }

  eliminarCampania(id: number): void {
    if (confirm('¿Eliminar esta campaña?')) {
      this.campaniaService.eliminar(id).subscribe(() => {
        this.campanias = this.campanias.filter(c => c.id !== id);
      });
    }
  }

  editarCampania(id: number): void {
    this.router.navigate(['/campanias/editar', id]);
  }
}

