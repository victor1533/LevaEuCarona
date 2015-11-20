package com.orochi.utfpr.levaeu.Escopo;
import com.orochi.utfpr.levaeu.Reputacao.Reputacao;

import java.io.Serializable;
import java.util.Date;import java.util.List;

/**
 * 
 */
public class Carona implements Serializable {

    /**
     * Default constructor
     */
    public Carona() {
    }
    private int codCarona;
    private Pessoa motorista;
    private List<Pessoa> saposNaCarona;
    private List<Pessoa> saposNaEspera;
    private int numVagas;
    private Local origem;
    private Local destino;
    private Date dataHoraPartida;
    private Reputacao avaliacaoCarona;

    public int getCodCarona() {
        return codCarona;
    }

    public Date getDataHoraPartida() {
        return dataHoraPartida;
    }

    public void setDataHoraPartida(Date dataHoraPartida) {
        this.dataHoraPartida = dataHoraPartida;
    }

    public void setCodCarona(int codCarona) {
        this.codCarona = codCarona;
    }

    public Pessoa getMotorista() {
        return motorista;
    }

    public void setMotorista(Pessoa motorista) {
        this.motorista = motorista;
    }

    public List<Pessoa> getSaposNaCarona() {
        return saposNaCarona;
    }

    public void setSaposNaCarona(List<Pessoa> saposNaCarona) {
        this.saposNaCarona = saposNaCarona;
    }

    public List<Pessoa> getSaposNaEspera() {
        return saposNaEspera;
    }

    public void setSaposNaEspera(List<Pessoa> saposNaEspera) {
        this.saposNaEspera = saposNaEspera;
    }

    public int getNumVagas() {
        return numVagas;
    }

    public void setNumVagas(int numVagas) {
        this.numVagas = numVagas;
    }

    public Local getOrigem() {
        return origem;
    }

    public void setOrigem(Local origem) {
        this.origem = origem;
    }

    public Local getDestino() {
        return destino;
    }

    public void setDestino(Local destino) {
        this.destino = destino;
    }


    public Reputacao getAvaliacaoCarona() {
        return avaliacaoCarona;
    }

    public void setAvaliacaoCarona(Reputacao avaliacaoCarona) {
        this.avaliacaoCarona = avaliacaoCarona;
    }

    /**
     * Adiciona um sapo nesta carona. Retorna true se realizou a inclusão com sucesso.
     * Retorna false se houve falha na inserção do sapo na carona ou o limite de sapos já foi atingido
     * @param sapo 
     * @return
     */

    public boolean isCheia(){
        return this.saposNaCarona.size() == this.numVagas;
    }
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

    @Override
    public boolean equals(Object obj){
        Carona carona = (Carona) obj;
        if(carona.getCodCarona()==this.getCodCarona()){
            return true;
        }
            return false;
    }

}