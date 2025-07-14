import { Campania } from '../campañas/campania.model';

export interface Jornada {
  id?: number;
  campania: Campania;
  fecha: string; // 'YYYY-MM-DD'
  horaInicio: string; // 'HH:mm'
  horaFin: string;    // 'HH:mm'
}
