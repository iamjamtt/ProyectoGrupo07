package com.example.proyectogrupo07;

import android.os.Parcel;
import android.os.Parcelable;

public class Producto implements Parcelable {
    public int producto_id;
    public String pronombre;
    public String prodescripcion;
    public double proprecio;
    public String proimagen;

    public Producto(int producto_id, String pronombre, String prodescripcion, double proprecio, String proimagen) {
        this.producto_id = producto_id;
        this.pronombre = pronombre;
        this.prodescripcion = prodescripcion;
        this.proprecio = proprecio;
        this.proimagen = proimagen;
    }

    public int getProduct_id() {
        return producto_id;
    }

    public void setProduct_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public String getPronombre() {
        return pronombre;
    }

    public void setPronombre(String pronombre) {
        this.pronombre = pronombre;
    }

    public String getProdescripcion() {
        return prodescripcion;
    }


    public void setProdescripcion(String prodescripcion) {
        this.prodescripcion = prodescripcion;
    }

    public double getProprecio() {
        return proprecio;
    }

    public void setProprecio(double proprecio) {
        this.proprecio = proprecio;
    }

    public String getProimagen() {
        return proimagen;
    }

    public void setProimagen(String proimagen) {
        this.proimagen = proimagen;
    }

    protected Producto(Parcel in) {
        producto_id = in.readInt();
        pronombre = in.readString();
        prodescripcion = in.readString();
        proprecio = in.readDouble();
        proimagen = in.readString();
    }

    public static final Creator<Producto> CREATOR = new Creator<Producto>() {
        @Override
        public Producto createFromParcel(Parcel in) {
            return new Producto(in);
        }

        @Override
        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(producto_id);
        dest.writeString(pronombre);
        dest.writeString(prodescripcion);
        dest.writeDouble(proprecio);
        dest.writeString(proimagen);
    }
}
