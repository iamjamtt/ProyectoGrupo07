package com.example.proyectogrupo07;

public class Producto {
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
}
