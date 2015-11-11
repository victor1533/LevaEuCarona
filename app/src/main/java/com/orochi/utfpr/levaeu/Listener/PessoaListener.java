package com.orochi.utfpr.levaeu.Listener;

import com.orochi.utfpr.levaeu.Carona;
import com.orochi.utfpr.levaeu.Pessoa;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Poisson on 10/11/2015.
 */
public interface PessoaListener {

    @POST("view/pessoa/new.php")
    Call<RespostaWS> cadastrar(@Body Pessoa pessoa);

    @POST("view/carona/new.php")
    Call<RespostaWS> criaCarona(@Body Carona carona);

    @FormUrlEncoded
    @POST("view/pessoa/mudarTipo.php")
    Call<RespostaWS> mudarTipo(@Field("codPessoa") int codPessoa);

    @POST("view/carona/getAll.php")
    Call<List<Carona>> getAllCaronas();

    @FormUrlEncoded
    @POST("view/pessoa/requisitarCarona.php")
    Call<RespostaWS> requisitarCarona(@Body Carona carona, @Field("codPessoa") int codPessoa);


}
