package com.pfariasmunoz.indensalessystem.data.models;

/**
 * Created by Pablo Farias on 10-04-17.
 */

public class Client {
    private String client;
    private String rut;
    private Long descuento;
    private String direccion;

    public Client() {
    }

    public Client(String client, String rut, Long descuento, String direccion) {
        this.client = client;
        this.rut = rut;
        this.descuento = descuento;
        this.direccion = direccion;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Long getDescuento() {
        return descuento;
    }

    public void setDescuento(Long descuento) {
        this.descuento = descuento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
