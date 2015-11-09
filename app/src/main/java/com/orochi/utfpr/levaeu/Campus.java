package com.orochi.utfpr.levaeu;
import java.lang.String;import java.util.*;

/**
 * 
 */
public class Campus {

    /**
     * Default constructor
     */
    public Campus() {
    }

    /**
     * 
     */
    private int codCampus;

    /**
     * 
     */
    private String nome;
    public String getStringCodCampus(){
        return (this.codCampus < 10 ? "0" + this.codCampus : "" + this.codCampus);
    }


    public void setCodCampus(int codCampus) {
        this.codCampus = codCampus;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodCampus() {
        return codCampus;
    }

    public static Campus getCampoMourao(){
        Campus campus = new Campus();
        campus.setNome("Campo Mourão");
        campus.setCodCampus(3);
        return campus;
    }
}