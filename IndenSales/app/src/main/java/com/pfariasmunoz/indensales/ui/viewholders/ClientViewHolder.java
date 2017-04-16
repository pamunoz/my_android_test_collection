package com.pfariasmunoz.indensales.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pfariasmunoz.indensales.R;
import com.pfariasmunoz.indensales.data.models.Client;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Pablo Farias on 16-04-17.
 */

public class ClientViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_client_name)
    TextView mNameTextView;
    @BindView(R.id.tv_client_rut)
    TextView mRutTextView;
    @BindView(R.id.tv_client_adress)
    TextView mAdressTextView;
    @BindView(R.id.tv_client_discount)
    TextView mDiscountTextView;

    public ClientViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setViewsText(Client client) {
        mNameTextView.setText(client.getNombre());
        mRutTextView.setText(client.getRut());
        mAdressTextView.setText("Temporal Text");
        mDiscountTextView.setText(client.getDescuento());
    }
}
