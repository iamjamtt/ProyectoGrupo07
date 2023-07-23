package com.example.proyectogrupo07;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("usuario")
    Call<ApiResp> postUsuarioRegistro(@Body Usuario usuario);

    @POST("usuario/auth")
    Call<ApiResp> postUsuarioLogin(@Body Login login);

}
