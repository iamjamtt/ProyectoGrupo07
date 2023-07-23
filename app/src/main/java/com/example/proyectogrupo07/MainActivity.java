package com.example.proyectogrupo07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ApiInterface apiInterface;

    EditText txtUsuario, txtPassword;
    Button btnIniciar, btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Inicializamos Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.50:4000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Obtenemos la instancia del servicio de la API
        apiInterface = retrofit.create(ApiInterface.class);

        //O
        txtUsuario = findViewById(R.id.txtUsuario);
        txtPassword = findViewById(R.id.txtPassword);


        Button btnIniciar = findViewById(R.id.btnIniciar);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Obtenemos los datos del formulario
                String correo = txtUsuario.getText().toString();
                String contrasena = txtPassword.getText().toString();

                //Creamos el objeto de solicitud de inicio de sesion
                Login login = new Login(correo, contrasena);

                //Realizamos la solicitud POST para el inicio de sesion
                Call<ApiResp> call = apiInterface.postUsuarioLogin(login);
                call.enqueue(new Callback<ApiResp>() {
                    @Override
                    public void onResponse(Call<ApiResp> call, Response<ApiResp> response) {
                        if(response.isSuccessful()){
                            ApiResp apiResp = response.body();
                            Toast.makeText(MainActivity.this, "Inicio de Sesion Exitosa",Toast.LENGTH_SHORT).show();

                            if(apiResp != null){
                                String message = apiResp.getMessage();

                                if (message != null && message.equals("content not found")) {
                                    Toast.makeText(MainActivity.this, "Usuario o Contraseña Incorrectas",Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(MainActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                // Si loginResponse es nulo, hubo un problema en la respuesta de la API.
                                Toast.makeText(MainActivity.this, "Error en la respuesta de la API", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            // La solicitud falló
                            Toast.makeText(MainActivity.this, "Error en inicio de sesión", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResp> call, Throwable t) {
                        // Manejar el error en caso de que la solicitud falle (por ejemplo, mostrar un mensaje de error).
                        Toast.makeText(MainActivity.this, "E " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("API", "Er: " + t.getMessage());
                    }
                });


            }
        });

    }
}