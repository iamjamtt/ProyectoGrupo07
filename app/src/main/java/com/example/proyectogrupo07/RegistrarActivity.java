package com.example.proyectogrupo07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrarActivity extends AppCompatActivity {
    EditText txtNombre, txtApellido, txtCorreo, txtContrasena;
    Button btnRegistrar, btnRegresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarusuario);

        // Obtenemos los datos de los campos de texto y botones
        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtContrasena = findViewById(R.id.txtContraseña);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegresar = findViewById(R.id.btnRegresarRegistro);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNombre.getText().toString().trim();
                String apellido = txtApellido.getText().toString().trim();
                String correo = txtCorreo.getText().toString().trim();
                String contrasena = txtContrasena.getText().toString().trim();

                if (!nombre.isEmpty() && !apellido.isEmpty() && !correo.isEmpty() && !contrasena.isEmpty()) {
                    registerUsuario(nombre, apellido, correo, contrasena);
                } else {
                    Toast.makeText(RegistrarActivity.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewLogin();
            }
        });
    }

    private void registerUsuario(String nombre, String apellido, String correo, String contrasena) {
        ApiInterface apiInterface = ApiCliente.getRetrofitInstance().create(ApiInterface.class);
        Usuario usuarioData = new Usuario(nombre, apellido, correo, contrasena);

        retrofit2.Call<List<Usuario>> call = apiInterface.postRegistro(usuarioData);
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Usuario>> call, Response<List<Usuario>> response) {
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
                        Toast.makeText(RegistrarActivity.this, "¡Registro exitoso! Usuario: " + nombre, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegistrarActivity.this, "No se registro el usuario", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegistrarActivity.this, "Error en la comunicación con el servidor 1", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(RegistrarActivity.this, "Error en la comunicación con el servidor 2", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void viewLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}