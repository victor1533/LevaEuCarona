package com.orochi.utfpr.levaeu.Escopo;
import java.io.Serializable;

/**
 * 
 */
public class CoordenadaGPS implements Serializable {

    /**
     * Default constructor
     */
    public CoordenadaGPS() {
    }

    private double latitude;
    private double longitude;
    private double precisao;

    public CoordenadaGPS(double latitude, double longitude, double precisao) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.precisao = precisao;
    }


    public double getPrecisao() {
        return precisao;
    }

    public void setPrecisao(double precisao) {
        this.precisao = precisao;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {

        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    @Override
    public boolean equals(Object obj){
        CoordenadaGPS cord = (CoordenadaGPS) obj;
        if((cord.getLatitude()==this.getLatitude())&& (cord.getLongitude()==this.getLongitude())){
            return true;
        }
        return false;
    }

}