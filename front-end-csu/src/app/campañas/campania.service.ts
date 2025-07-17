import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Campania } from './campania.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CampaniaService {
  private apiUrl = 'http://localhost:8080/api/campanias';

  constructor(private http: HttpClient) {}

  listar(): Observable<Campania[]> {
    return this.http.get<Campania[]>(this.apiUrl);
  }

  obtener(id: number): Observable<Campania> {
    return this.http.get<Campania>(`${this.apiUrl}/${id}`);
  }

  crear(campania: Campania): Observable<any> {
    return this.http.post(this.apiUrl, campania);
  }

  actualizar(campania: Campania): Observable<any> {
    return this.http.put(`${this.apiUrl}/${campania.id}`, campania);
  }

  eliminar(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}

