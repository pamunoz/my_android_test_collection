package com.pfariasmunoz.appindenandroid.data.models;

/**
 * Created by Pablo Farias on 06-04-17.
 */

public class Client {

    private String mNombre;
    private String mRut;
    private Long mDescuento;

    public Client() {
    }

    public Client(String nombre, String rut, Long descuento) {
        mNombre = nombre;
        mRut = rut;
        mDescuento = descuento;
    }

    public String getNombre() {
        return mNombre;
    }

    public void setNombre(String nombre) {
        mNombre = nombre;
    }

    public String getRut() {
        return mRut;
    }

    public void setRut(String rut) {
        mRut = rut;
    }

    public Long getDescuento() {
        return mDescuento;
    }

    public void setDescuento(Long descuento) {
        mDescuento = descuento;
    }
}
