package com.orochi.utfpr.levaeu.Escopo;
import com.google.android.gms.location.places.Place;

import java.io.Serializable;
import java.lang.String;

/**
 * 
 */
public class Local implements Serializable {

    /**
     * Default constructor
     */
    public Local() {
    }
    private int codLocal;
    private String endereco;
    private CoordenadaGPS coordenada;
    private String nomeLocal;
    public int getCodLocal() {
        return codLocal;
    }

    public String getNomeLocal() {
        return nomeLocal;
    }

    public void setNomeLocal(String nomeLocal) {
        this.nomeLocal = nomeLocal;
    }

    public void setCodLocal(int codLocal) {
        this.codLocal = codLocal;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public CoordenadaGPS getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(CoordenadaGPS coordenada) {
        this.coordenada = coordenada;
    }

    @Override
    public boolean equals(Object obj){
        Local local = (Local) obj;
        if(local.getCodLocal()==this.getCodLocal()){
            return true;
        }
        return false;
    }

    public static Local PlaceToLocal(Place place){
        Local local = new Local();
        local.setEndereco("" + (place.getAddress() == null ? "" : place.getAddress()));
        local.setNomeLocal("" + (place.getName() == null? place.getAddress() : place.getName()));

        local.setCoordenada(new CoordenadaGPS(place.getLatLng().latitude, place.getLatLng().longitude, 0));
        return local;
    }
}