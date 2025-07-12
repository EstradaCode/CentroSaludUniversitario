export interface Usuario {
  id?: number;
  nombre: string;
  apellido: string;
  dni: number;
  telefono: number;
  username: string;
  password: string;
  email: string;
  rol: 'ADMIN' | 'SALUD' | 'VISITANTE';
  matricula: number;
  enabled?: boolean;
}

