import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Jornada } from './jornada.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JornadaService {
  private apiUrl = 'http://localhost:8080/api/jornadas';

  constructor(private http: HttpClient) {}

  listar(): Observable<Jornada[]> {
    return this.http.get<Jornada[]>(this.apiUrl);
  }

  obtener(id: number): Observable<Jornada> {
    return this.http.get<Jornada>(`${this.apiUrl}/${id}`);
  }

  crear(jornada: Jornada): Observable<Jornada> {
    return this.http.post<Jornada>(this.apiUrl, jornada);
  }

  actualizar(jornada: Jornada): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${jornada.id}`, jornada);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
