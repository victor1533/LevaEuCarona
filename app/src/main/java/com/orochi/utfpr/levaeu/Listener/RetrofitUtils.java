package com.orochi.utfpr.levaeu.Listener;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Poisson on 10/11/2015.
 */
public class RetrofitUtils {

    public static Retrofit getRetrofit(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://youpobre.tk/projetocarona/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
