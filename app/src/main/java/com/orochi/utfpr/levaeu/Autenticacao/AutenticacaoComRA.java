package com.orochi.utfpr.levaeu.Autenticacao;

/**
 * 
 */
public class AutenticacaoComRA  implements Autenticacao{

    public String RA;
    public String senha;

    public AutenticacaoComRA(String RA, String senha) {
        this.RA = RA;
        this.senha = senha;
    }

    public String getRA() {
        return RA;
    }

    public void setRA(String RA) {
        this.RA = RA;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean logar() {
        // TODO implement here
        return false;
    }



}