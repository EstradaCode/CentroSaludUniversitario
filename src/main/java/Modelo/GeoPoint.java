package Modelo;


import jakarta.persistence.*;

@Embeddable
public class GeoPoint {

    private double latitud;
    private double longitud;


    public GeoPoint() {
    }

    public GeoPoint(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
