package com.orochi.utfpr.levaeu;

/**
 * Created by Poisson on 11/11/2015.
 */
public class Sessao {
    private static Sessao ourInstance = new Sessao();

    public static Sessao getInstance() {
        return ourInstance;
    }
    private Pessoa pessoaLogada;

    public Pessoa getPessoaLogada() {
        return pessoaLogada;
    }

    public void setPessoaLogada(Pessoa pessoaLogada) {
        this.pessoaLogada = pessoaLogada;
    }

    private Sessao() {
    }
}
