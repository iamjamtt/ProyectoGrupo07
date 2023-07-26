    package com.example.proyectogrupo07;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Toast;

    import java.util.ArrayList;
    import java.util.List;

    public class CarritoCompra extends AppCompatActivity {

//        private static List<Producto> cartItems;
//        private RecyclerView recyclerView;
//        private MyAdapterCarrito adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_carrito_compra);

//            // Obtener los productos seleccionados del Intent
//            cartItems = new ArrayList<>();
//            Intent intent = getIntent();
//            Producto productoSeleccionado = intent.getParcelableExtra("productoSeleccionado");
//            if (productoSeleccionado != null) {
//                cartItems.add(productoSeleccionado);
//            }
//
//            // Inicializar el RecyclerView
//            recyclerView = findViewById(R.id.recyclerViewCarritoCompra);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//            // Inicializar el adaptador y asignarlo al RecyclerView
//            adapter = new MyAdapterCarrito(cartItems);
//            recyclerView.setAdapter(adapter);
        }

        //Onclik para el boton de seguir comprando
        public void onSeguitComprandoButtonClick(View view) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }

        // Método para agregar el producto al carrito
//        public void agregarProductoAlCarrito(Producto producto) {
//            cartItems.add(producto);
//            adapter.notifyDataSetChanged();
//        }

        //Onclik para el boton de pagar
        public void onPagarButtonClick(View view) {
            // Toast para mostrar que se esta pagando


            //Mandar a la vista de PagoActivity
            Intent intent = new Intent(CarritoCompra.this, PagoActivity.class);
            startActivity(intent);
        }

//        public static List<Producto> getCartItems() {
//            return cartItems;
//        }

//        public void onPagarButtonClick(View view) {
//            // Asegúrate de que el carrito no esté vacío antes de pagar.
//            if (cartItems.isEmpty()) {
//                Toast.makeText(this, "El carrito está vacío", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            // Pagar
//            Toast.makeText(this, "Pagando...", Toast.LENGTH_SHORT).show();
//
//            // Limpiar el carrito
//            limpiarCarrito();
//            adapter.notifyDataSetChanged();
//        }

    }