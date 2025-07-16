import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Campania } from './campania.model';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class CampaniaService {
  private url = 'http://localhost:8080/api/campanias';

  constructor(private http: HttpClient) {}

  listar(): Observable<Campania[]> {
    return this.http.get<Campania[]>(this.url);
  }

  obtener(id: number): Observable<Campania> {
    return this.http.get<Campania>(`${this.url}/${id}`);
  }

  crear(campania: Campania): Observable<Campania> {
    return this.http.post<Campania>(this.url, campania);
  }

  actualizar(campania: Campania): Observable<void> {
    return this.http.put<void>(`${this.url}/${campania.id}`, campania);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}
