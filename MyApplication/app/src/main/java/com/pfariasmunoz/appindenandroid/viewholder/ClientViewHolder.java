package com.pfariasmunoz.appindenandroid.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pfariasmunoz.appindenandroid.R;
import com.pfariasmunoz.appindenandroid.data.models.Client;

/**
 * Created by Pablo Farias on 12-04-17.
 */

public class ClientViewHolder extends RecyclerView.ViewHolder {

    TextView mClientName;
    TextView mClientRut;
    TextView mClientAdress;
    TextView mClientDiscount;


    public ClientViewHolder(View itemView) {
        super(itemView);

        mClientName = (TextView) itemView.findViewById(R.id.tv_client_name);
        mClientRut = (TextView) itemView.findViewById(R.id.tv_client_rut);
        mClientAdress = (TextView) itemView.findViewById(R.id.tv_client_adress);
        mClientDiscount = (TextView) itemView.findViewById(R.id.tv_client_descuento);

    }

    public void bindToClient(Client client) {
        mClientName.setText(client.nombre);
        mClientRut.setText(client.rut);
        mClientAdress.setText("Temporal adress");
        mClientDiscount.setText(String.valueOf(client.descuento));

    }
}
