package com.example.proyectogrupo07;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegistrarActivity extends AppCompatActivity {
    EditText txtNombre, txtApellido, txtCorreo, txtContraseña;
    Button btnRegistrar;

    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarusuario);
        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtContraseña = findViewById(R.id.txtContraseña);

        //Inicializamos el Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.50:4000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Obtenemos la instancia del servicio de la API
        apiInterface = retrofit.create(ApiInterface.class);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                // Obtenemos los datos del formulario (vista)
                String nombre = txtNombre.getText().toString();
                String apellido = txtApellido.getText().toString();
                String correo = txtCorreo.getText().toString();
                String contrasena = txtContraseña.getText().toString();

                // Crear el objeto de usuario
                Usuario usuario = new Usuario(nombre, apellido, correo, contrasena);

                // Realizar la solicitud POST para el registro de usuario
                retrofit2.Call<ApiResp> call = apiInterface.postUsuarioRegistro(usuario);
                call.enqueue(new Callback<ApiResp>() {
                    @Override
                    public void onResponse(retrofit2.Call<ApiResp> call, Response<ApiResp> response) {
                        // Procesar la respuesta si es necesaria
                    }

                    @Override
                    public void onFailure(Call<ApiResp> call, Throwable t) {
                        // Manejar el error en caso de que la solicitud falle
                    }
                });

            }
        });

    }

}