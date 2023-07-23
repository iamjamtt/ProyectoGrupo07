package com.example.proyectogrupo07;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<Product> listaProductos;

    private static final String urlApi = "http://192.168.1.40:4000/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();

        // Inicializar la RecyclerView y el adaptador
        recyclerView = findViewById(R.id.rvAllProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this, new ArrayList<>());

        // Asignar el adaptador a la RecyclerView
        recyclerView.setAdapter(adapter);

        // Obtener los productos de la API
        getProductos();
    }

    private void getProductos() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<List<Product>> call = apiInterface.getProductos();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(HomeActivity.this, "Error al obtener los productos", Toast.LENGTH_SHORT).show();
                    return;
                }

                listaProductos = response.body();
                adapter.setProductos(listaProductos);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Error al obtener los productos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}