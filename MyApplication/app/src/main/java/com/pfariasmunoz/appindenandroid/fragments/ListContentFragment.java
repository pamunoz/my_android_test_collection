package com.pfariasmunoz.appindenandroid.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pfariasmunoz.appindenandroid.R;

/**
 * Created by Pablo Farias on 06-04-17.
 */

public class ListContentFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nombreTextView;
        public TextView rutTextView;
        public TextView direccionTextView;
        public TextView zonaTextView;
        public TextView ciudadTextView;
        public TextView comunaTextView;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_list_client, parent, false));
            nombreTextView = (TextView) itemView.findViewById(R.id.tv_name);
            rutTextView = (TextView) itemView.findViewById(R.id.tv_rut);
            direccionTextView = (TextView) itemView.findViewById(R.id.tv_direccion);
            zonaTextView = (TextView) itemView.findViewById(R.id.tv_zona);
            ciudadTextView = (TextView) itemView.findViewById(R.id.tv_ciudad);
            comunaTextView = (TextView) itemView.findViewById(R.id.tv_comuna);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Context context = v.getContext();
//                    Intent intent = new Intent(context, DetailActivity.class);
//                    intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
//                    context.startActivity(intent);
                }
            });
        }
    }

    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 20;

        private final String[] mNombres;
        private final String[] mRuts;
        private final String[] mDirecciones;
        private final String[] mZonas;
        private final String[] mCiudades;
        private final String[] mComunas;

        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            mNombres = resources.getStringArray(R.array.nombres);
            mRuts = resources.getStringArray(R.array.ruts);
            mDirecciones = resources.getStringArray(R.array.direcciones);
            mZonas = resources.getStringArray(R.array.zonas);
            mCiudades = resources.getStringArray(R.array.ciudades);
            mComunas = resources.getStringArray(R.array.comunas);
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.nombreTextView.setText(mNombres[position]);
            holder.rutTextView.setText(mRuts[position]);
            holder.direccionTextView.setText(mDirecciones[position]);
            holder.zonaTextView.setText(mZonas[position]);
            holder.ciudadTextView.setText(mCiudades[position]);
            holder.comunaTextView.setText(mComunas[position]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}
