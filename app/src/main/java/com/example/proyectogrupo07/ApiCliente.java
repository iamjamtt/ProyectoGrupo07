package com.example.proyectogrupo07;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCliente {
    private static Retrofit retrofit;

    //private static final String BASE_URL = "http://192.168.1.85:4000/api/";
    private static final String BASE_URL = "http://192.168.0.127:4000/api/";


    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
