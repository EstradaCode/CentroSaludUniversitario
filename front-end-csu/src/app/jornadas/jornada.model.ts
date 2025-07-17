import { Campania } from '../campañas/campania.model';

export interface Jornada {
  id?: number;
  fecha: string;         // formato yyyy-MM-dd
  horaInicio: string;    // formato HH:mm
  horaFin: string;       // formato HH:mm
  campania: {
    id: number;
  };
}

