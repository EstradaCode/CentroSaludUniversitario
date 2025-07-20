export interface Campania {
  id?: number;
  nombre: string;
  rutaArchivoEncuesta: string;
  fechaInicio: string; // "2025-08-01"
  fechaFin: string; // "2025-08-20"
  organizacionSocial: {
    id: number;
    nombre?: string;
    domicilio?: string;
    actividadPrincipal?: string;
    barrio?: {
      id: number;
      nombre?: string;
      zonas?: {
        id: number;
        nombre: string;
        geoPoint?: { latitud: number; longitud: number }[];
      }[];
    };
  };
  zona?: any[]; // posiblemente lista de barrios o zonas
  jornadas?: any[]; // editable desde su propia vista
  encuestadores?: any[]; // editable desde su propia vista
}
