package com.pfariasmunoz.appindenandroid.data.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Pablo Farias on 09-04-17.
 */
@IgnoreExtraProperties
public class Adress {
    private String ciudad;
    private String comuna;
    private String direccion;
    private Long telefono;
    private String zona;

    public Adress() {
    }

    public Adress(String ciudad, String comuna, String direccion, Long telefono, String zona) {
        this.ciudad = ciudad;
        this.comuna = comuna;
        this.direccion = direccion;
        this.telefono = telefono;
        this.zona = zona;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }
}
