package com.pfariasmunoz.indensales.data.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Pablo Farias on 29-04-17.
 */
@IgnoreExtraProperties
public class ArticleSale {
    private int cantidad;
    private long total;

    public ArticleSale() {
    }

    public ArticleSale(int cantidad, long total) {
        this.cantidad = cantidad;
        this.total = total;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Exclude
    public void addQuantity() {
        cantidad ++;
    }
}
