package com.pfariasmunoz.indensales.ui.viewholders;

import android.provider.UserDictionary;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pfariasmunoz.indensales.R;
import com.pfariasmunoz.indensales.data.models.Client;
import com.pfariasmunoz.indensales.utils.TextHelper;

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
    @BindView(R.id.bt_add_sale)
    Button mAddSaleButton;


    public ClientViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setTextOnViews(Client client) {
        mNameTextView.setText(client.getNombre().trim());
        mRutTextView.setText(client.getRut());
    }

    public Button getAddSaleButton() {
        return mAddSaleButton;
    }
}
