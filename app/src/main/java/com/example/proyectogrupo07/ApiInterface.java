package com.example.proyectogrupo07;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("producto")
    Call<List<Producto>> getProductos();

    @POST("usuario/auth")
    Call<List<Usuario>> postUsuarioLogin(@Body Login login);

    @POST("usuario")
    Call<List<Usuario>> postRegistro(@Body Usuario usuario);
}
