import { Routes } from '@angular/router';

//export const routes: Routes = [];
import { UsuarioListadoComponent } from './usuarios/usuario-listado/usuario-listado.component';
import { UsuarioFormularioComponent } from './usuarios/usuario-formulario/usuario-formulario.component';
export const routes: Routes = [
  { path: '', redirectTo: 'usuarios', pathMatch: 'full' },
  { path: 'usuarios', component: UsuarioListadoComponent },
  { path: 'usuarios/nuevo', component: UsuarioFormularioComponent }
  
];

