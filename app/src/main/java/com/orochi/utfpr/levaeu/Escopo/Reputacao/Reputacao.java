package com.orochi.utfpr.levaeu.Escopo.Reputacao;
import com.orochi.utfpr.levaeu.Escopo.Pessoa;

import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Reputacao implements Serializable {

    private int codReputacao;
    private int likes;
    private int dislikes;


    public int getCodReputacao() {
        return codReputacao;
    }

    public void setCodReputacao(int codReputacao) {
        this.codReputacao = codReputacao;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}