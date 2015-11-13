package com.orochi.utfpr.levaeu;
import java.io.Serializable;
import java.lang.String;import java.util.*;

/**
 * 
 */
public class Telefone implements Serializable {

    /**
     * Default constructor
     */
    public Telefone() {
    }

    private String ddd;
    private String numero;
    private String tipo;

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object obj){
        Telefone tel = (Telefone) obj;
        if((tel.getDdd().equals(this.getDdd()))&&(tel.getNumero().equals(this.getNumero()))&& (tel.getTipo().equals(this.getTipo()))){
            return true;
        }
        return false;

    }

}