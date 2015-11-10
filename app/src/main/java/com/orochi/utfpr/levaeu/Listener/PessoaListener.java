package com.orochi.utfpr.levaeu.Listener;

import com.orochi.utfpr.levaeu.Carona;
import com.orochi.utfpr.levaeu.Pessoa;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Poisson on 10/11/2015.
 */
public interface PessoaListener {

    @POST("view/pessoa/new.php")
    Call<RespostaWS> cadastrar(@Body Pessoa pessoa);

    @POST("view/carona/new.php")
    Call<RespostaWS> criaCarona(@Body Carona carona);
}
