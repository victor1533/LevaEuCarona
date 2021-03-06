package com.orochi.utfpr.levaeu.Escopo;
import com.orochi.utfpr.levaeu.Escopo.Autenticacao.AutenticacaoComRA;
import com.orochi.utfpr.levaeu.Escopo.Reputacao.Reputacao;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

/**
 * 
 */
public class Pessoa implements Serializable {
    private int codPessoa;
    private DadosPessoais dados;
    private AutenticacaoComRA autenticacao;
    private Reputacao reputacao;
    private Campus campus;
    private Curso curso;
    private String tpcurcodnr;
    private String alcuordemnr;
    public Pessoa() {
    }
    @Override
    public String toString(){
        return this.getDados().getNome();
    }
    public int getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(int codPessoa) {
        this.codPessoa = codPessoa;
    }

    public Pessoa(int RA, String senha, Campus campus) {
        this.dados = new DadosPessoais();
        this.autenticacao = new AutenticacaoComRA("" + RA, senha);
        this.campus = campus;
    }
    public String getStringRA(){
        return "" + this.getAutenticacao().getRA();
    }

    public DadosPessoais getDados() {
        return dados;
    }

    public void setDados(DadosPessoais dados) {
        this.dados = dados;
    }

    public AutenticacaoComRA getAutenticacao() {
        return autenticacao;
    }

    public void setAutenticacao(AutenticacaoComRA autenticacao) {
        this.autenticacao = autenticacao;
    }

    public Reputacao getReputacao() {
        return reputacao;
    }

    public void setReputacao(Reputacao reputacao) {
        this.reputacao = reputacao;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getAlcuordemnr() {
        return alcuordemnr;
    }

    public void setAlcuordemnr(String alcuordemnr) {
        this.alcuordemnr = alcuordemnr;
    }

    public String getTpcurcodnr() {
        return tpcurcodnr;
    }

    public void setTpcurcodnr(String tpcurcodnr) {
        this.tpcurcodnr = tpcurcodnr;
    }


    @Override
    public boolean equals(Object obj){
        Pessoa pessoa = (Pessoa)obj;
        if(pessoa.getCodPessoa()==this.getCodPessoa()){
            return true;
        }
        return false;
    }

}