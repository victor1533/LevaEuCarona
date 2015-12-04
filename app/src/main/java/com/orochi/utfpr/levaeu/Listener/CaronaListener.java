package com.orochi.utfpr.levaeu.Listener;

import com.orochi.utfpr.levaeu.Escopo.Carona;
import com.orochi.utfpr.levaeu.Reputacao.Dislike;
import com.orochi.utfpr.levaeu.Reputacao.Like;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by a1602632 on 25/11/15.
 */
public interface CaronaListener {


    @GET("view/carona/darLike.php")
    Call<RespostaWS> darLike(@Query("codPessoa") int codPessoa,@Query("codCarona") int codCarona);

    @GET("view/carona/darDislike.php")
    Call<RespostaWS> darDislike(@Query("codPessoa") int codPessoa,@Query("codCarona") int codCarona);
}
