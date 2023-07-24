package com.example.proyectogrupo07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CarritoCompra extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapterCarrito adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito_compra);
    }

    //Onclik para el boton de seguir comprando
    public void onSeguitComprandoButtonClick(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

}