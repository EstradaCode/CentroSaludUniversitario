export interface Campania {
  id?: number;
  nombre: string;
  rutaArchivoEncuesta: string;
  fechaInicio: string; // formatoque usamos:yyyy-mm-dd
  fechaFin: string;
  organizacionSocial: {
    id: number;
  };
}
