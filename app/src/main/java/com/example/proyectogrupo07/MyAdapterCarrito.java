package com.example.proyectogrupo07;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

public class MyAdapterCarrito extends RecyclerView.Adapter<MyAdapterCarrito.CartViewHolder>  {
    private Context context;
    private List<Producto> cartItems;

    public MyAdapterCarrito(List<Producto> cartItems) {
        this.cartItems = cartItems;
    }

    public void setCartItems(List<Producto> cartItems) {
        this.cartItems = cartItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_carrito, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Producto producto = cartItems.get(position);
        holder.txtNombreProducto.setText(producto.getPronombre());
        holder.txtDescripcion.setText(producto.getProdescripcion());
        double precio = producto.getProprecio();
        DecimalFormat df = new DecimalFormat("#.00");
        String precioFormateado = df.format(precio);
        holder.txtPrecio.setText(String.valueOf("S/. " + precioFormateado));
        Glide.with(holder.itemView.getContext()).load(producto.getProimagen()).into(holder.imgProducto);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombreProducto;
        TextView txtDescripcion;
        TextView txtPrecio;

        ImageView imgProducto;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombreProducto = itemView.findViewById(R.id.txtNombreProductoCarrito);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcionCarrito);
            txtPrecio = itemView.findViewById(R.id.txtPrecioCarrito);
            imgProducto = itemView.findViewById(R.id.imgProductoCarrito);
        }
    }
}
