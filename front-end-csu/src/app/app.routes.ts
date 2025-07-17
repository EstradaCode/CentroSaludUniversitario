import { Routes } from '@angular/router';

//export const routes: Routes = [];
import { UsuarioListadoComponent } from './usuarios/usuario-listado/usuario-listado.component';
import { UsuarioFormularioComponent } from './usuarios/usuario-formulario/usuario-formulario.component';
import { UsuarioEditarComponent } from './usuarios/usuario-editar/usuario-editar.component';
import { CampaniaListadoComponent } from './campañas/campania-listado/campania-listado.component';
import { CampaniaEditarComponent } from './campañas/campania-editar/campania-editar.component';
import { CampaniaFormularioComponent } from './campañas/campania-formulario/campania-formulario.component';
import { JornadaListadoComponent } from './jornadas/jornada-listado/jornada-listado.component';
import { JornadaFormularioComponent } from './jornadas/jornada-formulario/jornada-formulario.component';
import { EncuestadorFormularioComponent } from './encuestadores/encuestador-formulario/encuestador-formulario.component';
import { EncuestadorEditarComponent } from './encuestadores/encuestador-editar/encuestador-editar.component';
import { EncuestadorListadoComponent } from './encuestadores/encuestador-listado/encuestador-listado.component';
import { JornadaEditarComponent } from './jornadas/jornada-editar/jornada-editar.component';
export const routes: Routes = [
  { path: '', redirectTo: 'usuarios', pathMatch: 'full' },
  { path: 'usuarios', component: UsuarioListadoComponent },
  { path: 'usuarios/nuevo', component: UsuarioFormularioComponent },
  { path: 'usuarios/editar/:id', component: UsuarioEditarComponent },
  { path: 'campanias', component: CampaniaListadoComponent },
  { path: 'campanias/nueva', component: CampaniaFormularioComponent },
  { path: 'campanias/editar/:id', component: CampaniaEditarComponent },
  { path: 'jornadas', component: JornadaListadoComponent },
  { path: 'jornadas/nueva', component:JornadaFormularioComponent},
  {path: 'jornadas/editar/:id', component:JornadaEditarComponent},
  { path: 'encuestadores', component: EncuestadorListadoComponent },
  { path: 'encuestadores/nuevo', component: EncuestadorFormularioComponent },
  { path: 'encuestadores/editar/:id', component: EncuestadorEditarComponent },

];

