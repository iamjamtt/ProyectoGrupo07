package com.example.proyectogrupo07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ApiInterface apiInterface;

    EditText txtUsuario, txtPassword;
    Button btnIniciar, btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Verificar si hay una sesión activa
        sesion();

        txtUsuario = findViewById(R.id.txtUsuario);
        txtPassword = findViewById(R.id.txtPassword);
        btnIniciar = findViewById(R.id.btnIniciar);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = txtUsuario.getText().toString().trim();
                String contrasena = txtPassword.getText().toString().trim();

                if (!correo.isEmpty() && !contrasena.isEmpty()) {
                    loginUser(correo, contrasena);
                } else {
                    Toast.makeText(LoginActivity.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewRegistro();
            }
        });
    }

    private void sesion() {
        // Obtener el token de la sesión
        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        int usuario_id = preferences.getInt("usuario_id", 0);
        String nombre = preferences.getString("nombre", null);
        String apellido = preferences.getString("apellido", null);
        String correo = preferences.getString("correo", null);
        String contrasena = preferences.getString("contrasena", null);

        if(usuario_id != 0 && nombre != null && apellido != null && correo != null && contrasena != null) {
            // Ir al activity Home
            ViewHome(usuario_id, nombre, apellido, correo, contrasena);
        }
    }

    private void loginUser(String correo, String contrasena) {
        ApiInterface apiInterface = ApiCliente.getRetrofitInstance().create(ApiInterface.class);
        Login loginData = new Login(correo, contrasena);

        Call<List<Usuario>> call = apiInterface.postUsuarioLogin(loginData);
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (response.isSuccessful()) {
                    List<Usuario> apiResponseList = response.body();
                    if (apiResponseList != null && !apiResponseList.isEmpty()) {
                        Usuario usuario = apiResponseList.get(0);
                        // Aquí puedes obtener los datos del usuario, por ejemplo:
                        int usuario_id = usuario.getUsuario_id();
                        String nombre = usuario.getNombre();
                        String apellido = usuario.getApellido();
                        String correo = usuario.getCorreo();
                        String contrasena = usuario.getContrasena();

                        // Continúa con la lógica de inicio de sesión según tus necesidades
                        Toast.makeText(LoginActivity.this, "¡Login exitoso! Usuario: " + nombre, Toast.LENGTH_SHORT).show();

                        // Ir al activity Home
                        ViewHome(usuario_id, nombre, apellido, correo, contrasena);
                    } else {
                        Toast.makeText(LoginActivity.this, "Respuesta vacía del servidor", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Error en la comunicación con el servidor 1", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error en la comunicación con el servidor 2", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ViewRegistro() {
        // ir al activity de registro
        Intent intent = new Intent(this, RegistrarActivity.class);
        startActivity(intent);
    }

    private void ViewHome(int usuario_id, String nombre, String apellido, String correo, String contrasena) {
        // ir al activity de registro
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("usuario_id", usuario_id);
        intent.putExtra("nombre", nombre);
        intent.putExtra("apellido", apellido);
        intent.putExtra("correo", correo);
        intent.putExtra("contrasena", contrasena);
        startActivity(intent);
    }
}