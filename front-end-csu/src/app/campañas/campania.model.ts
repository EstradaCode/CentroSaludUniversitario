export interface Campania {
  id?: number;
  nombre: string;
  rutaArchivoEncuesta: string;
  fechaInicio: string;
  fechaFin: string;
  organizacionSocial: { id: number };
  zona?: { id: number }[];              // barrios
  jornadas?: { id: number }[];          // se relacionan aparte
  encuestadores?: { id: number }[];     // igual
}

