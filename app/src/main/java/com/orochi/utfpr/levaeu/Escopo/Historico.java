package com.orochi.utfpr.levaeu.Escopo;
import java.io.Serializable;
import java.util.List;

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


    public Pessoa getDono() {
        return dono;
    }

    public void setDono(Pessoa dono) {
        this.dono = dono;
    }

    public List<Carona> getCaronas() {
        return caronas;
    }

    public void setCaronas(List<Carona> caronas) {
        this.caronas = caronas;
    }

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
    @Override
    public boolean equals(Object obj){
        Historico hist = (Historico) obj;
        if((hist.getDono().equals(this.getDono()))&&(hist.getCaronas().containsAll(this.getCaronas()))){
            return true;
        }
        return false;
    }


}