package com.drean.projects.autos.pojo;

public class Pedido {
    private int id;
    private String nombreAuto;
    private String imgAuto;
    private String nombre;
    private int dni;
    private int celular;
    private String email;
    private int cantidad;
    private double precioUnidad;
    private double igv;
    private String fecha;
    private double total;

    public Pedido(int id, String nombreAuto, String imgAuto, String nombre, int dni, int celular, String email, int cantidad, double precioUnidad, double igv, String fecha, double total) {
        this.id = id;
        this.nombreAuto = nombreAuto;
        this.imgAuto = imgAuto;
        this.nombre = nombre;
        this.dni = dni;
        this.celular = celular;
        this.email = email;
        this.cantidad = cantidad;
        this.precioUnidad = precioUnidad;
        this.igv = igv;
        this.fecha = fecha;
        this.total = total;
    }

    public Pedido() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreAuto() {
        return nombreAuto;
    }

    public void setNombreAuto(String nombreAuto) {
        this.nombreAuto = nombreAuto;
    }

    public String getImgAuto() {
        return imgAuto;
    }

    public void setImgAuto(String imgAuto) {
        this.imgAuto = imgAuto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(double precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
