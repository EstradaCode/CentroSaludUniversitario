export interface Campania {
  id?: number;
  nombre: string;
  rutaArchivoEncuesta: string;
  fechaInicio: string; // usar string para binding en formularios
  fechaFin: string;
  zona: number[]; // IDs de barrios
  encuestadores: number[]; // IDs de encuestadores
  organizacionSocial: number; // ID de la organización
}