package com.orochi.utfpr.levaeu.Listener;

import com.google.gson.GsonBuilder;

import java.util.Date;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Poisson on 10/11/2015.
 */
public class RetrofitUtils {
    public static GsonConverterFactory GsonConverterFactoryGen(){
        return GsonConverterFactory.create(
                new GsonBuilder()
                        .registerTypeAdapter(Date.class, new DateDeserializer())
                        .create());

    }
    public static Retrofit getRetrofit(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://youpobre.tk/projetocarona/")
                .addConverterFactory(GsonConverterFactoryGen())

                .build();
        return retrofit;
    }
}
