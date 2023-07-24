package com.example.proyectogrupo07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleProducto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        //Obtener el id del producto seleccionado
        Intent intent = getIntent();
        int idProducto = intent.getIntExtra("idProducto", 0);

        //Obtener el producto correspondiente al id
        mostrarDetallesProducto(idProducto);

    }

    public void onRegresarButtonClick(View view) {
        onBackPressed();
    }

    public void onAgregarCarritoButtonClick(View view) {
        Toast.makeText(this, "Producto agregado al carrito", Toast.LENGTH_SHORT).show();
    }

    public void onCarritoButtonClick(View view) {
        Intent intent = new Intent(this, CarritoCompra.class);
        startActivity(intent);
    }

    //Mostrar los detalles del producto, incluyendo la imagen, nombre, descripción y precio
    public void mostrarDetallesProducto(int idProducto) {
        // Crear una instancia de Retrofit para realizar la petición HTTP de retrofit
        ApiInterface apiInterface = ApiCliente.getRetrofitInstance().create(ApiInterface.class);

        // Crear la petición HTTP GET a la ruta "producto" de la API
        Call<List<Producto>> call = apiInterface.getProductoById(idProducto);
        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Obtener el producto del cuerpo de la respuesta
                    List<Producto> listaProductos = response.body();
                    if (!listaProductos.isEmpty()) {
                        Producto producto = listaProductos.get(0);

                        // Ahora que tienes el objeto Producto, puedes mostrar los detalles en la interfaz de usuario
                        mostrarDetallesEnInterfaz(producto);
                    } else {
                        // La lista de productos está vacía, no se encontró el producto con el ID especificado
                        // Realiza una acción o muestra un mensaje apropiado en la interfaz
                        Toast.makeText(DetalleProducto.this, "No se encontró el producto", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // La respuesta no fue exitosa o el cuerpo de la respuesta es nulo
                    // Realiza una acción o muestra un mensaje apropiado en la interfaz
                    Toast.makeText(DetalleProducto.this, "Error en la respuesta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                // Error en la comunicación con el servidor o en la realización de la petición
                // Muestra un mensaje de error o realiza una acción apropiada en la interfaz
                Toast.makeText(DetalleProducto.this, "Error en la comunicación con el servidor", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void mostrarDetallesEnInterfaz(Producto producto) {
        // Obtén referencias a los elementos visuales en la interfaz
        TextView txtNombre = findViewById(R.id.txt_nombre_producto);
        TextView txtDescripcion = findViewById(R.id.txt_detalle_producto);
        TextView txtPrecio = findViewById(R.id.txt_precio_producto);
        ImageView imgProducto = findViewById(R.id.imageProducto);

        // Carga los datos del producto en los elementos visuales
        txtNombre.setText(producto.getPronombre());
        txtDescripcion.setText(producto.getProdescripcion());
        DecimalFormat df = new DecimalFormat("#.00");
        String precioFormateado = df.format(producto.getProprecio());
        txtPrecio.setText("S/. " + precioFormateado);
        // Cargar la imagen utilizando Glide
        Glide.with(this).load(producto.getProimagen()).into(imgProducto);

    }

}