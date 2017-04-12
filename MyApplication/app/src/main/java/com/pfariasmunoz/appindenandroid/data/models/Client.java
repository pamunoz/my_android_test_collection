package com.pfariasmunoz.appindenandroid.data.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Pablo Farias on 06-04-17.
 */
@IgnoreExtraProperties
public class Client {

    public String nombre;
    public String rut;
    public Long descuento;

    public Client() {
    }

    public Client(String nombre, String rut, Long descuento) {
        this.nombre = nombre;
        this.rut = rut;
        this.descuento = descuento;
    }
}
