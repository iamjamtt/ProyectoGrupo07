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

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {
    private Context context;
    private List<Product> productos;

    public MyAdapter(Context context, List<Product> productos) {
        this.context = context;
        this.productos = productos;
    }

    public void setProductos(List<Product> productos) {
        this.productos = productos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_products, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product producto = productos.get(position);

        holder.tvNombre.setText(producto.getPronombre());
        holder.tvDescripcion.setText(producto.getProdescripcion());
        holder.tvPrecio.setText(String.valueOf(producto.getProprecio()));

        // Cargar la imagen utilizando Glide o alguna otra biblioteca de manejo de imágenes
        Glide.with(context).load(producto.getProimagen()).into(holder.ivImagen);
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvDescripcion, tvPrecio;
        ImageView ivImagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNameProduct);
            tvDescripcion = itemView.findViewById(R.id.tvDescriptionProduct);
            tvPrecio = itemView.findViewById(R.id.tvPriceProduct);
            ivImagen = itemView.findViewById(R.id.ivProductImage);
        }
    }
}
