export interface Jornada {
  id?: number;
  fecha: string; // formato 'YYYY-MM-DD'
  horaInicio: string; // formato 'HH:mm'
  horaFin: string;    // formato 'HH:mm'
  campania: {
    id: number;
  };
}


