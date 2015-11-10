package com.orochi.utfpr.levaeu;
import java.io.Serializable;
import java.util.*;import java.util.List;

/**
 * 
 */
public class Historico implements Serializable {

    /**
     * Default constructor
     */
    public Historico() {
    }

    /**
     * 
     */
    private List<Carona> caronas;

    /**
     * 
     */
    private Pessoa dono;




    /**
     * A função verifica se a pessoa enviada por parametro já teve alguma carona neste histórico.
     * Retorna true se sim e false se não.
     * @param pessoa 
     * @return
     */
    public boolean isInCaronas(Pessoa pessoa) {
        // TODO implement here
        return false;
    }

}