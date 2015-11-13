package com.orochi.utfpr.levaeu.Reputacao;
import com.orochi.utfpr.levaeu.Pessoa;

import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Reputacao implements Serializable {

    /**
     * Default constructor
     */
    public Reputacao() {
    }

    /**
     * 
     */
    private List<Like> likes;

    public List<Dislike> getDislikes() {
        return dislikes;
    }

    public void setDislikes(List<Dislike> dislikes) {
        this.dislikes = dislikes;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    /**

     * 
     */
    private List<Dislike> dislikes;



    /**
     * @param pessoa
     */
    public void addLike(Pessoa pessoa) {
        // TODO implement here
    }

    /**
     * @param pessoa
     */
    public void addDislike(Pessoa pessoa) {
        // TODO implement here
    }

    @Override
    public boolean equals(Object obj){
        Reputacao rep = (Reputacao) obj;
        if((rep.getDislikes().containsAll(this.getDislikes())&&(rep.getLikes().containsAll(this.getLikes())))){
            return true;
        }
        return false;
    }

}