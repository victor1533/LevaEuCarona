package com.orochi.utfpr.levaeu.Reputacao;
import com.orochi.utfpr.levaeu.Escopo.Pessoa;

import java.io.Serializable;

/**
 * 
 */
public class Dislike implements Serializable {

    private int codDeslike;

    public int getCodDeslike() {
        return codDeslike;
    }

    public void setCodDeslike(int codDeslike) {
        this.codDeslike = codDeslike;
    }

    public Dislike() {
    }

    public Pessoa getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(Pessoa avaliador) {
        this.avaliador = avaliador;
    }

    /**

     * 
     */
    private Pessoa avaliador;

    @Override
    public boolean equals(Object obj){
        Dislike dislike = (Dislike) obj;
        if(dislike.getCodDeslike()==this.getCodDeslike()){
            return true;
        }
        return false;
    }

}