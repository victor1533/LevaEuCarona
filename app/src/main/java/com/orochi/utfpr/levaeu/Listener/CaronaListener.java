package com.orochi.utfpr.levaeu.Listener;

import com.orochi.utfpr.levaeu.Escopo.Carona;
import com.orochi.utfpr.levaeu.Reputacao.Dislike;
import com.orochi.utfpr.levaeu.Reputacao.Like;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by a1602632 on 25/11/15.
 */
public interface CaronaListener {


    @POST("view/carona/darLike.php")
    Call<RespostaWS> darLike(@Body Like like);

    @POST("view/carona/darLike.php")
    Call<RespostaWS> darDislike(@Body Dislike dislike);
}
