package Persistencia;

import Modelo.Zona;

import java.util.List;

public interface ZonaDao extends GenericDao<Zona> {


    List<Zona> findByNombre(String nombre);
}
