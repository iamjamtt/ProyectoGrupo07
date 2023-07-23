package com.example.proyectogrupo07;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("producto")
    Call<List<Product>> getProductos();

}
