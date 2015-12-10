package com.orochi.utfpr.levaeu.Retrofit.Listener;

/**
 * Created by Poisson on 10/11/2015.
 */
public class RespostaWS {
    private String resultado;
    private boolean sucesso;
    private int cod;
    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
}
