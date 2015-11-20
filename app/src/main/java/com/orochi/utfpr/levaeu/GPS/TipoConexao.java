package com.orochi.utfpr.levaeu.GPS;

/**
 * Created by Poisson on 16/08/2015.
 */
public enum TipoConexao {
    WIFI("WIFI"),
    _3G("3G"),
    SEM_CONEXAO("Sem Conexao");

    private final String valor;
    TipoConexao(String valorOpcao){ valor = valorOpcao; }
    public String toString(){ return valor; }

}
