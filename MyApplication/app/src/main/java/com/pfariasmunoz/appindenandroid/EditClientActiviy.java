package com.pfariasmunoz.appindenandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pfariasmunoz.appindenandroid.data.FirebaseUtil;
import com.pfariasmunoz.appindenandroid.data.models.Client;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditClientActiviy extends AppCompatActivity {

    @BindView(R.id.tv_client_name)
    TextView mClientNameTextView;
    @BindView(R.id.et_client_name)
    EditText mClientNameEditText;
    @BindView(R.id.tv_client_rut)
    TextView mClientRutTextView;
    @BindView(R.id.et_client_rut)
    EditText mClientRutEditText;
    @BindView(R.id.tv_client_descuento)
    TextView mClientDiscountTextView;
    @BindView(R.id.et_client_descuento)
    EditText mClientDiscountEditText;

    private String mClientName;
    private String mClientRut;
    private Long mDiscount;

    private Client mClient;

    // Database
    FirebaseDatabase mDatabase;
    DatabaseReference mClientsReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client);

        ButterKnife.bind(this);

        mDatabase = FirebaseDatabase.getInstance();
        String tablaClientes = getResources().getString(R.string.table_clients);
        mClientsReference = mDatabase.getReference().child(tablaClientes));
    }

    @OnClick(R.id.save_client)
    public void writeNewClient() {
        String nombre = mClientNameEditText.getText().toString();
        String rut = mClientRutEditText.getText().toString();
        Long descuento = Long.valueOf(mClientDiscountEditText.getText().toString());
        Toast.makeText(this, nombre + rut + String.valueOf(descuento), Toast.LENGTH_SHORT).show();
        mClient = new Client(nombre, rut, descuento);
        mClientsReference.push().setValue(mClient);
        finish();
    }
}
