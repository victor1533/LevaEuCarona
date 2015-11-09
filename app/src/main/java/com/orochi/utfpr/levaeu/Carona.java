package com.orochi.utfpr.levaeu;
import com.orochi.utfpr.levaeu.Reputacao.Reputacao;

import java.util.*;import java.util.Date;import java.util.List;

/**
 * 
 */
public class Carona {

    /**
     * Default constructor
     */
    public Carona() {
    }

    /**
     * 
     */
    private Motorista motorista;

    /**
     * 
     */
    private List<Sapo> saposNaCarona;

    /**
     * 
     */
    private List<Sapo> saposNaEspera;

    /**
     * 
     */
    private int numVagas;

    /**
     * 
     */
    private Local origem;

    /**
     * 
     */
    private Local destino;

    /**
     * 
     */
    private Date horarioPartida;

    /**
     * 
     */
    private Reputacao avaliacaoCarona;







    /**
     * Adiciona um sapo nesta carona. Retorna true se realizou a inclusão com sucesso.
     * Retorna false se houve falha na inserção do sapo na carona ou o limite de sapos já foi atingido
     * @param sapo 
     * @return
     */
    public boolean adicionarSapo(Sapo sapo) {
        // TODO implement here
        return false;
    }

    /**
     * @param sapo 
     * @return
     */
    public boolean removerSapo(Sapo sapo) {
        // TODO implement here
        return false;
    }

    /**
     * @param novaOrigem 
     * @return
     */
    public boolean alterarOrigem(Local novaOrigem) {
        // TODO implement here
        return false;
    }

    /**
     * @param novoDestino 
     * @return
     */
    public boolean alterarDestino(Local novoDestino) {
        // TODO implement here
        return false;
    }

    /**
     * @param date 
     * @return
     */
    public boolean alterarHorarioDePartida(Date date) {
        // TODO implement here
        return false;
    }

}