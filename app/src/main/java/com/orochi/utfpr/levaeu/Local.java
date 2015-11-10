package com.orochi.utfpr.levaeu;
import java.io.Serializable;
import java.lang.String;import java.util.*;

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

    public int getCodLocal() {
        return codLocal;
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
}