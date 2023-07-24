package com.example.proyectogrupo07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DetalleProducto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        //Obtener el id del producto seleccionado
        Intent intent = getIntent();
        int idProducto = intent.getIntExtra("idProducto", 0);

        //Obtener el producto correspondiente al id

    }

    public void onRegresarButtonClick(View view) {
        onBackPressed();
    }
}