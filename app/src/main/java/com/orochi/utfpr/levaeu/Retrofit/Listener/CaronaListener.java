package com.orochi.utfpr.levaeu.Retrofit.Listener;

import retrofit.Call;
import retrofit.http.GET;
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
