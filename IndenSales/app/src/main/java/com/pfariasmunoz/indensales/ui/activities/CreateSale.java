package com.pfariasmunoz.indensales.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pfariasmunoz.indensales.R;
import com.pfariasmunoz.indensales.data.FirebaseDb;
import com.pfariasmunoz.indensales.data.models.Address;
import com.pfariasmunoz.indensales.data.models.Article;
import com.pfariasmunoz.indensales.data.models.ArticleSale;
import com.pfariasmunoz.indensales.data.models.Client;
import com.pfariasmunoz.indensales.data.models.Sale;
import com.pfariasmunoz.indensales.data.models.SaleData;
import com.pfariasmunoz.indensales.ui.adapters.ArticlesSaleAdapter;
import com.pfariasmunoz.indensales.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateSale extends BaseActivity implements ArticlesSaleAdapter.ButtonClickListener {

    public static final String TAG = CreateSale.class.getSimpleName();

    // Bind views
    @BindView(R.id.lv_articles_sale_list)
    RecyclerView mArticlesSaleRecyclerView;
    @BindView(R.id.tv_client_name)
    TextView mClientNameTextView;
    @BindView(R.id.tv_client_rut)
    TextView mClientRutTextView;
    @BindView(R.id.tv_client_address)
    TextView mClientAddressTextView;
    @BindView(R.id.bt_create_sale)
    Button mCreateSaleButton;
    @BindView(R.id.tv_articles_amount)
    TextView mArticlesAmountTextView;
    @BindView(R.id.tv_label_articles_amount)
    TextView mLabelArticlesAmountTextView;
    @BindView(R.id.tv_label_sale_total_price)
    TextView mLabelSaleTotalTextView;
    @BindView(R.id.tv_sale_total_price)
    TextView mSaleTotalTextView;
    @BindView(R.id.tv_is_sales_aproved)
    TextView mSaleAprovedTextView;

    // Data
    private List<SaleData> mSaleDataList;
    private ArticlesSaleAdapter mAdapter;
    private String mSaleId;
    private String mUserId;
    private String mClientId;
    private String mClientAddressUid;
    private long mTotal;
    private String mAmount;

    // Listeners for the data
    private ValueEventListener mClientListener;
    private ValueEventListener mClientAddressListener;
    private ChildEventListener mSaleListener;
    private ChildEventListener mArticlesListener;
    private ChildEventListener mArticlesSalesListener;

    // DbQueries
    private Query mClientQuery;
    private Query mClientAddressesQuery;
    private Query mArticlesQuery;
    private Query mSalesQuery;
    private Query mArticlesSalesQuery;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sale);


        ButterKnife.bind(this);

        mSaleId = "1";
        mUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mClientId = getIntent().getStringExtra(Constants.CLIENT_ID_KEY);
        mClientAddressUid = getIntent().getStringExtra(Constants.ADDRESS_ID_KEY);

        // Initialize Firebase references
        mClientQuery = FirebaseDb.sClientsRef.child(mClientId);
        mClientAddressesQuery = FirebaseDb.sClientAdressRef.child(mClientId).child(mClientAddressUid);
        mArticlesQuery = FirebaseDb.sArcilesRefOrderByDescription.limitToFirst(50);
        mArticlesSalesQuery = FirebaseDb.sArticlesSalesRef.child(mSaleId);

        mSaleDataList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mArticlesSaleRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration divider = new DividerItemDecoration(mArticlesSaleRecyclerView.getContext(), layoutManager.getOrientation());
        mArticlesSaleRecyclerView.addItemDecoration(divider);

        mAdapter = new ArticlesSaleAdapter(this, mSaleDataList);
        mArticlesSaleRecyclerView.setAdapter(mAdapter);

        // initialize the firebase listeners
        attachDatabaseReadListener();

    }

    private void attachDatabaseReadListener() {
        mClientListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getValue() != null) {
                    Client client = dataSnapshot.getValue(Client.class);
                    Log.i(TAG, " attachDatabaseReadListener creating client: " + client.getNombre());

                    mClientNameTextView.setText(client.getNombre());
                    mClientRutTextView.setText(client.getRut());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mClientAddressListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getValue() != null) {
                    Address address = dataSnapshot.getValue(Address.class);
                    Log.i(TAG, "ADDRESS: " + mClientAddressUid);
                    String direccion = address.getDireccion();
                    String comuna = address.getComuna();
                    String ciudad = address.getCiudad();
                    String longAddress = direccion + "\n" + comuna + ", " + ciudad + ".";
                    mClientAddressTextView.setText(longAddress);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mSaleListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.exists() && dataSnapshot.getValue() != null) {
                    Sale sale = dataSnapshot.getValue(Sale.class);
                    mTotal = sale.getTotal();
                    mSaleTotalTextView.setText(String.valueOf(mTotal));
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        addArticleData();

        // set the listeners to the queries
        if (mClientListener != null) {
            mClientQuery.addListenerForSingleValueEvent(mClientListener);
        }
        if (mClientAddressListener != null) {
            mClientAddressesQuery.addListenerForSingleValueEvent(mClientAddressListener);
        }
        if (mArticlesListener != null) {
            mArticlesQuery.addChildEventListener(mArticlesListener);
        }
        if (mArticlesSalesListener != null) {
            mArticlesSalesQuery.addChildEventListener(mArticlesSalesListener);
        }
    }

    @Override
    public void onAddButtonClick(SaleData saleData) {
//        if (saleData != null) {
//            Article article = saleData.getArticle();
//            ArticleSale articleSale = saleData.getArticleSale();
//            Long price = Long.valueOf(article.getPrecio().trim());
//            int amount = articleSale.getCantidad() + 1;
//
//        }
    }

    @Override
    public void onSubtractButtonClick(SaleData saleData) {

    }

    private void detachDatabaseReadListner() {
        if (mClientListener != null) {
            mClientQuery.removeEventListener(mClientListener);
        }
        if (mClientAddressListener != null) {
            mClientAddressesQuery.removeEventListener(mClientAddressListener);
        }
        if (mSaleListener != null) {
            mSalesQuery.removeEventListener(mSaleListener);
        }
        if (mArticlesListener != null) {
            mArticlesQuery.removeEventListener(mArticlesListener);
        }
        if (mArticlesSalesListener != null) {
            mArticlesSalesQuery.removeEventListener(mArticlesSalesListener);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        detachDatabaseReadListner();
    }

    private void addArticleData() {
        final SaleData newSaleData = new SaleData();
        mArticlesListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Article article = dataSnapshot.getValue(Article.class);

                String articleId = dataSnapshot.getKey();

                newSaleData.setArticleId(articleId);

                newSaleData.setArticle(article);

                ArticleSale articleSale = new ArticleSale(0, 0);
                newSaleData.setArticleSale(articleSale);
                mSaleDataList.add(newSaleData);
                Log.i(TAG, "SIZE OF THE DATA *****: " + String.valueOf(mSaleDataList.size()));

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };



        mArticlesSalesListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.exists() && dataSnapshot.getValue() != null) {
                    ArticleSale articleSale = dataSnapshot.getValue(ArticleSale.class);
                    newSaleData.setArticleSale(articleSale);
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mSaleDataList.add(newSaleData);

    }
}
