export interface Encuestador {
  id?: number;
  nombre: string;
  apellido: string;
  dni: number;
  telefono: number;
  horasTrabajadas: number;
  barrio: {
    id: number;
  };
}