package com.orochi.utfpr.levaeu.Escopo;
import com.orochi.utfpr.levaeu.Escopo.Autenticacao.AutenticacaoComRA;

import java.io.Serializable;
import java.util.List;

/**
 * 
 */
public class Sapo extends Pessoa implements Serializable {

    /**
     * Default constructor
     */
    public Sapo() {
    }
    public Sapo(int RA, String senha, Campus campus) {
        this.setDados(new DadosPessoais());
        this.setAutenticacao(new AutenticacaoComRA("" + RA, senha));
        this.setCampus(campus);
    }
    /**
     * @return
     */
    public List<Carona> getCaronasProximas() {
        // TODO implement here
        return null;
    }

    /**
     * @param carona 
     * @return
     */
    public boolean sairDaCarona(Carona carona) {
        // TODO implement here
        return false;
    }

}