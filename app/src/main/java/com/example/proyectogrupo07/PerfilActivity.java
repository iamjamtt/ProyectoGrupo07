package com.example.proyectogrupo07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PerfilActivity extends AppCompatActivity {

    TextView tvNombre, tvApellido, tvCorreo;
    private Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Inicializar los TextView
        tvNombre = findViewById(R.id.tvNombrePerfilId);
        tvApellido = findViewById(R.id.tvApellidoPerfilId);
        tvCorreo = findViewById(R.id.tvCorreoPerfilId);
        btnRegresar = findViewById(R.id.btnRegresarPerfilId);

        // Obtener los datos del login y guardarlos en SharedPreferences
        obtenerDatos();

        // Regresar a la pantalla de inicio
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHome();
            }
        });
    }

    private void viewHome() {
        Intent intent = new Intent(this, HomeActivity.class);
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
            tvNombre.setText("Nombre: " + nombre);
            tvApellido.setText("Apellido: " + apellido);
            tvCorreo.setText("Correo: " + correo);
        } else {
            Toast.makeText(this, "Error al obtener los datos", Toast.LENGTH_SHORT).show();
        }
    }
}