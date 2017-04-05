package com.pfariasmunoz.sistemadeventastest;

import android.text.TextUtils;

import java.util.ArrayList;

/**
 * Created by Pablo Farias on 05-04-17.
 */

public class FakeDataUtil {
    public static ArrayList<String> sNombres = new ArrayList<>();
    public static ArrayList<String> sRuts = new ArrayList<>();
    public static ArrayList<String> sDirecciones = new ArrayList<>();
    public static ArrayList<String> sZonas = new ArrayList<>();
    public static ArrayList<String> sCiudades = new ArrayList<>();
    public static ArrayList<String> sComunas = new ArrayList<>();


    public FakeDataUtil() {
        setData();
    }

    public static void setData() {
        int namesNum = 10;
        for (int i = 0; i < namesNum; i++) {
            String digit = Integer.toString(i);
            // nombres
            sNombres.add("Name" + digit);
            String rut = "";

            for (int j = 0; j < 9; j++) {
                rut += digit;
            }
            // ruts
            sRuts.add(rut);
            sDirecciones.add("Direccion" + digit);
            sZonas.add("Zona" + digit);
            sCiudades.add("Ciudad" + digit);
            sComunas.add("Comuna" + digit);

        }
    }
}
