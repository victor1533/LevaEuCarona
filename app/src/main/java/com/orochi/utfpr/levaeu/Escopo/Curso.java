package com.orochi.utfpr.levaeu.Escopo;
import java.io.Serializable;
import java.lang.String;

/**
 * 
 */
public class Curso implements Serializable {
    private String nome;
    private int codCurso;
    /**
     * Default constructor
     */
    public Curso() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(int codCurso) {
        this.codCurso = codCurso;
    }

    @Override
    public boolean equals(Object obj){
        Curso  curso = (Curso) obj;
        if(curso.getCodCurso()==this.getCodCurso()){
            return true;
        }
        return false;
    }
}