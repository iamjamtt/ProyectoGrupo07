package com.example.proyectogrupo07;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {
    private Context context;
    private List<Producto> productos;

    public MyAdapter(Context context, List<Producto> productos) {
        this.context = context;
        this.productos = productos;
    }

    public void setProductos(List<Producto> productos) {
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
        Producto producto = productos.get(position);

        holder.tvNombre.setText(producto.getPronombre());
        holder.tvDescripcion.setText(producto.getProdescripcion());
        double precio = producto.getProprecio();
        DecimalFormat df = new DecimalFormat("#.00");
        String precioFormateado = df.format(precio);
        holder.tvPrecio.setText("S/. " + precioFormateado);

        // Cargar la imagen utilizando Glide o alguna otra biblioteca de manejo de imágenes
        Glide.with(context).load(producto.getProimagen()).into(holder.ivImagen);

        // Agregar el OnClickListener al itemView (elemento raíz de la vista del producto)
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el producto correspondiente al clic
                Producto producto = productos.get(position);

                //Toast.makeText(context, "Producto: " + producto.getPronombre(), Toast.LENGTH_SHORT).show();

                int idProducto = producto.getProduct_id();

                // Crear un intent para abrir la actividad de detalle del producto
                Intent intent = new Intent(context, DetalleProducto.class);
                intent.putExtra("idProducto", idProducto);
                context.startActivity(intent);
            }
        });
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
