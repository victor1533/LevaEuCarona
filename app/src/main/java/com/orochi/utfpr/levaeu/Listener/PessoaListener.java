package com.orochi.utfpr.levaeu.Listener;

import com.orochi.utfpr.levaeu.Carona;
import com.orochi.utfpr.levaeu.Pessoa;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

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

    @GET("view/carona/getAll.php")
    Call<List<Carona>> getAllCaronas();

    @POST("view/pessoa/requisitarCarona.php")
    Call<RespostaWS> requisitarCarona(@Body Carona carona, @Query("codPessoa") int codPessoa);

    @POST("view/pessoa/confirmaNaCarona.php")
    Call<RespostaWS> confirmarCarona(@Body Carona carona, @Query("codPessoa") int codPessoa);

    @GET("view/pessoa/getCaronasCriadas.php")
    Call<List<Carona>> getCaronasCriadas(@Query("codPessoa") int codPessoa);

    @GET("view/pessoa/verCaronasQueEstou.php")
    Call<List<Carona>> getCaronasQueEstou(@Query("codPessoa") int codPessoa);


}
