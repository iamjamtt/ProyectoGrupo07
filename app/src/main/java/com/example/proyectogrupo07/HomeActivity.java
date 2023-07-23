package com.example.proyectogrupo07;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<Producto> listaProductos;

    TextView tvNombre, tvCorreo;
    private Button btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Inicializar el botón y obtener los datos del login y guardarlos en SharedPreferences
        btnCerrarSesion = findViewById(R.id.btnLogout);
        tvNombre = findViewById(R.id.tvNombreHombe);
        tvCorreo = findViewById(R.id.tvCorreoHome);
        obtenerDatos();

        // Inicializar la RecyclerView y el adaptador
        recyclerView = findViewById(R.id.rvAllProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this, new ArrayList<>());

        // Asignar el adaptador a la RecyclerView
        recyclerView.setAdapter(adapter);

        // Obtener los productos de la API
        getProductos();

        // Cerrar sesión
        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Eliminar el token de la sesión
                SharedPreferences preferences = getSharedPreferences(getString(R.string.pref_file), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();

                // Redirigir al login
                viewLogin();
                finish();
            }
        });
    }

    private void getProductos() {
        // Crear una instancia de Retrofit para realizar la petición HTTP
        ApiInterface apiInterface = ApiCliente.getRetrofitInstance().create(ApiInterface.class);

        // Crear la petición HTTP GET a la ruta "producto" de la API
        Call<List<Producto>> call = apiInterface.getProductos();

        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(HomeActivity.this, "Error al obtener los productos", Toast.LENGTH_SHORT).show();
                    return;
                }

                listaProductos = response.body();
                adapter.setProductos(listaProductos);
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Error al obtener los productos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void viewLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void obtenerDatos() {
        // Obtener los datos de SharedPreferences
        SharedPreferences preferences = getSharedPreferences(getString(R.string.pref_file), Context.MODE_PRIVATE);
        int usuario_id = preferences.getInt("usuario_id", 0);
        String nombre = preferences.getString("nombre", null);
        String apellido = preferences.getString("apellido", null);
        String correo = preferences.getString("correo", null);

        if (usuario_id != 0 && nombre != null && apellido != null && correo != null) {
            // Mostrar los datos en los TextView
            tvNombre.setText(nombre + " " + apellido);
            tvCorreo.setText(correo);
        } else {
            Toast.makeText(this, "Error al obtener los datos", Toast.LENGTH_SHORT).show();
        }
    }
}