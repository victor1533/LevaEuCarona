package com.orochi.utfpr.levaeu.Reputacao;
import com.orochi.utfpr.levaeu.Escopo.Pessoa;

import java.io.Serializable;

/**
 * 
 */
public class Like implements Serializable {

    private int codLike;

    public Pessoa getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(Pessoa avaliador) {
        this.avaliador = avaliador;
    }

    public int getCodLike() {
        return codLike;
    }

    public void setCodLike(int codLike) {
        this.codLike = codLike;
    }

    public Like() {
    }

    /**
     * 
     */
    private Pessoa avaliador;


    @Override
    public boolean equals(Object obj){
        Like like = (Like) obj;
        if(like.getCodLike()==this.getCodLike()){
            return true;
        }
        return false;
    }
}