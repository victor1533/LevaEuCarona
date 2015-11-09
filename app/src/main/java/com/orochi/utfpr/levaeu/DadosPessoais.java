package com.orochi.utfpr.levaeu;
import java.lang.String;import java.util.*;import java.util.Date;import java.util.List;

/**
 * 
 */
public class DadosPessoais {

    /**
     * Default constructor
     */
    private String nome;
    private Date dataNascimento;
    private Local enderecoResidencia;
    private List<Telefone> fones;
    private char sexo;
    private String email;

    public DadosPessoais() {
    }



    public static class Endereco {

        /**
         * Default constructor
         */
        public Endereco() {
        }

        /**
         *
         */
        private String endereco;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Local getEnderecoResidencia() {
        return enderecoResidencia;
    }

    public void setEnderecoResidencia(Local enderecoResidencia) {
        this.enderecoResidencia = enderecoResidencia;
    }

    public List<Telefone> getFones() {
        return fones;
    }

    public void setFones(List<Telefone> fones) {
        this.fones = fones;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}