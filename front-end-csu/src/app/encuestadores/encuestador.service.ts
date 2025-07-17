import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Encuestador } from './encuestador.model';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class EncuestadorService {
  private apiUrl = 'http://localhost:8080/api/encuestadores';

  constructor(private http: HttpClient) {}

  listar(): Observable<Encuestador[]> {
    return this.http.get<Encuestador[]>(this.apiUrl);
  }

  obtener(id: number): Observable<Encuestador> {
    return this.http.get<Encuestador>(`${this.apiUrl}/${id}`);
  }

  crear(encuestador: Encuestador): Observable<any> {
    return this.http.post(this.apiUrl, encuestador);
  }

  actualizar(encuestador: Encuestador): Observable<any> {
    return this.http.put(`${this.apiUrl}/${encuestador.id}`, encuestador);
  }

  eliminar(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
