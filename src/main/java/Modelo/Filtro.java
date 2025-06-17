package Modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Filtro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String valorSeleccionado;
    private String operacion;
    private Boolean isActive;

    public Filtro() {
    }
    public Filtro(String valorSeleccionado, String operacion, Boolean isActive) {
        this.valorSeleccionado = valorSeleccionado;
        this.operacion = operacion;
        this.isActive = isActive;
    }
    public String getValorSeleccionado() {
        return valorSeleccionado;
    }
    public void setValorSeleccionado(String valorSeleccionado) {
        this.valorSeleccionado = valorSeleccionado;
    }
    public String getOperacion() {
        return operacion;
    }
    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }
    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

}
