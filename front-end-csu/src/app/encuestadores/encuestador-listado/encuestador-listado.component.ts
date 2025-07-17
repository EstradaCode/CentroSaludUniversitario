import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { EncuestadorService } from '../encuestador.service';
import { Encuestador } from '../encuestador.model';

@Component({
  selector: 'app-encuestador-listado',
  standalone: true,
  templateUrl: './encuestador-listado.html',
  styleUrls: ['./encuestador-listado.scss'],
  imports: [
    CommonModule,
    RouterModule,
    MatTableModule,
    MatButtonModule
  ]
})
export class EncuestadorListadoComponent implements OnInit {
  encuestadores: Encuestador[] = [];

  constructor(private service: EncuestadorService) {}

  ngOnInit(): void {
    this.service.listar().subscribe(data => this.encuestadores = data);
  }

  eliminar(id: number): void {
    if (confirm('¿Eliminar encuestador?')) {
      this.service.eliminar(id).subscribe(() => {
        this.encuestadores = this.encuestadores.filter(e => e.id !== id);
      });
    }
  }
}
