package com.example.proyectogrupo07;

public class Usuario {
    public int usuario_id;
    public String nombre;
    public String apellido;
    public String correo;
    public String contrasena;

    public Usuario(String nombre, String apellido, String correo, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public int getUsuario_id() { return usuario_id; }

    public void setUsuario_id(int usuario_id) { this.usuario_id = usuario_id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }

    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getCorreo() { return correo; }

    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }

    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
}
