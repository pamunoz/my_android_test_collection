package com.pfariasmunoz.sistemadeventastest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Pablo Farias on 05-04-17.
 */

public class ClinetsAdapter extends RecyclerView.Adapter<ClinetsAdapter.ClientsViewHolder> {
    private int mNumberOfItems;

    private int viewHolderCount;

    public ClinetsAdapter(int numberOfItems) {
        this.mNumberOfItems = numberOfItems;
        viewHolderCount = 0;
    }

    @Override
    public ClientsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.item_row_clients;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);

        ClientsViewHolder viewHolder = new ClientsViewHolder(view);

        viewHolder.mDirTextView.setText(FakeDataUtil.sDirecciones.get(viewHolderCount));
        viewHolder.mRutTextView.setText(FakeDataUtil.sRuts.get(viewHolderCount));
        viewHolder.mZoneTextView.setText(FakeDataUtil.sRuts.get(viewHolderCount));
        viewHolder.mCiudadTextView.setText(FakeDataUtil.sCiudades.get(viewHolderCount));
        viewHolder.mComunaTextView.setText(FakeDataUtil.sComunas.get(viewHolderCount));

        viewHolderCount++;

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ClientsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ClientsViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {
        private ImageView mImgAvatar;
        private TextView mRutTextView, mDirTextView, mZoneTextView, mCiudadTextView, mComunaTextView;

        private static final String CLIENT_KEY = "CLIENT";


        public ClientsViewHolder(View itemView) {
            super(itemView);

            mImgAvatar = (ImageView) itemView.findViewById(R.id.img_avatar);
            mDirTextView = (TextView) itemView.findViewById(R.id.tv_direccion);
            mRutTextView = (TextView) itemView.findViewById(R.id.tv_rut);
            mZoneTextView = (TextView) itemView.findViewById(R.id.tv_zona);
            mCiudadTextView = (TextView) itemView.findViewById(R.id.tv_ciudad);
            mComunaTextView = (TextView) itemView.findViewById(R.id.tv_comuna);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("RecyclerView", "CLICK!");
        }

        public void bind(int itemPosition) {
            mDirTextView.setText(FakeDataUtil.sDirecciones.get(itemPosition));
            mRutTextView.setText(FakeDataUtil.sRuts.get(itemPosition));
            mZoneTextView.setText(FakeDataUtil.sRuts.get(itemPosition));
            mCiudadTextView.setText(FakeDataUtil.sCiudades.get(itemPosition));
            mComunaTextView.setText(FakeDataUtil.sComunas.get(itemPosition));
        }

    }
}
