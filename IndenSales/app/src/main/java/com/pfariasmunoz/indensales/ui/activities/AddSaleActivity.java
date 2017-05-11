package com.pfariasmunoz.indensales.ui.activities;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import com.pfariasmunoz.indensales.R;
import com.pfariasmunoz.indensales.data.FirebaseDb;
import com.pfariasmunoz.indensales.data.models.Address;
import com.pfariasmunoz.indensales.data.models.Article;
import com.pfariasmunoz.indensales.data.models.ArticleSale;
import com.pfariasmunoz.indensales.data.models.Client;
import com.pfariasmunoz.indensales.data.models.Sale;
import com.pfariasmunoz.indensales.ui.viewholders.ArticlesViewHolder;
import com.pfariasmunoz.indensales.utils.Constants;
import com.pfariasmunoz.indensales.utils.MathHelper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AddSaleActivity extends BaseActivity {
    public static final String TAG = AddSaleActivity.class.getSimpleName();

    private Long mTotalPrice;
    private int mTotalAmount;
    private String mDescriptionQuery;
    private String mSaleUid;

    private String mClientId;
    private FirebaseUser mUser;
    private String mUserId;
    private String mClientAddressId;

    private RecyclerView mArticlesListView;

    private TextView mClientNameTextView;
    private TextView mClientRutTextView;
    private TextView mClientAddressTextView;
    private TextView mArticleAmountTextView;
    private TextView mTotalSalesPriceTextView;
    private Button mCreateSaleButton;


    // Firebase instance variables
    private DatabaseReference mClientDatabaseReference;
    private ValueEventListener mClientValueEventListener;
    private DatabaseReference mClientAddressDatabaseReference;
    private ValueEventListener mClientAddressValueEventListener;

    private FirebaseRecyclerAdapter<Article, ArticlesViewHolder> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_add_sale);

        setTitle(getResources().getString(R.string.sales_activity_title));


        mTotalPrice = 0L;
        mTotalAmount = 0;
        mDescriptionQuery = "";


        // Initialize Firebase components
        mClientId = getIntent().getStringExtra(Constants.CLIENT_ID_KEY);
        mClientAddressId = "unknown";

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mUserId = mUser != null ? mUser.getUid() : "Unknown User";

        writeNewSale();



        //mArticlesDatabaseReference = mFirebaseDatabase.getReference().child("articulos");
        mClientDatabaseReference = FirebaseDb.sClientsRef.child(mClientId);
        mClientAddressDatabaseReference = FirebaseDb.sClientAdressRef.child(mClientId);

        initializeViews();

        setupAdapter();

        mArticlesListView.setAdapter(mAdapter);
        attachDatabaseReadListener();
    }

    private void setupAdapter() {
        mAdapter = getAdapter(FirebaseDb.getArticlesDescriptionQuery(mDescriptionQuery));
    }

    private FirebaseRecyclerAdapter<Article, ArticlesViewHolder> getAdapter(Query query) {
        return new FirebaseRecyclerAdapter<Article, ArticlesViewHolder>(
                Article.class,
                R.layout.item_article,
                ArticlesViewHolder.class,
                query
        ) {
            @Override
            protected void populateViewHolder(final ArticlesViewHolder viewHolder, final Article model, final int position) {

                final String ariticleKey = getRef(position).getKey();


                FirebaseDb.sArticlesSalesRef.child(mSaleUid).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                        long total = 0;
                        int amount = 0;
                        ArticleSale articleSale = null;
                        for (DataSnapshot data : iterable) {
                            articleSale = data.getValue(ArticleSale.class);
                            total += articleSale.getTotal();
                            amount += articleSale.getCantidad();
                        }

                        FirebaseDb.sSalesRef.child(mSaleUid).child("total").setValue(total);
                        mTotalSalesPriceTextView.setText(String.valueOf(total));
                        mArticleAmountTextView.setText(String.valueOf(amount));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                viewHolder.setDescriptionAndPrice(model);

                viewHolder.getAddArticleToSaleButton()
                        .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addArticleSale(FirebaseDb.sArticlesSalesRef.child(mSaleUid).child(ariticleKey), model);


                    }
                });

                viewHolder.getSubtractArticleFromSalesButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            }


        };

    }

    private void initializeViews() {
        // set the other views
        mClientNameTextView = (TextView) findViewById(R.id.tv_client_name);
        mClientRutTextView = (TextView) findViewById(R.id.tv_client_rut);
        mClientAddressTextView = (TextView) findViewById(R.id.tv_client_address);
        mArticleAmountTextView = (TextView) findViewById(R.id.tv_articles_amount);
        mArticleAmountTextView.setText(String.valueOf(mTotalAmount));
        mTotalSalesPriceTextView = (TextView) findViewById(R.id.tv_sale_total_price);
        mTotalSalesPriceTextView.setText(String.valueOf(mTotalPrice));
        mCreateSaleButton = (Button) findViewById(R.id.bt_crear_venta);
        mArticlesListView = (RecyclerView) findViewById(R.id.lv_articles_list);
        mArticlesListView.setLayoutManager(new LinearLayoutManager(this));
        mCreateSaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sale sale = new Sale(
                        false, String.valueOf(System.currentTimeMillis()),
                        mClientId,
                        mClientAddressId,
                        mUserId,
                        mTotalPrice);
                DatabaseReference ref = FirebaseDb.sSalesRef.push();

                ref.setValue(sale);

                String uid = ref.getKey();

                saveAllSalesArticles(uid);
            }
        });
    }

    private void attachDatabaseReadListener() {

        // Add listener to the clients
        if (mClientValueEventListener == null) {
            mClientValueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Client client = dataSnapshot.getValue(Client.class);
                    mClientNameTextView.setText(client.getNombre());
                    mClientRutTextView.setText(client.getRut());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            mClientDatabaseReference.addValueEventListener(mClientValueEventListener);
        }
        // Add listener to the client adress
        if (mClientAddressValueEventListener == null) {
            mClientAddressValueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                    for (DataSnapshot data : iterable) {
                        mClientAddressId = data.getKey();
                        String direccion = data.child("direccion").getValue(String.class);
                        String comuna = data.child("comuna").getValue(String.class);
                        String ciudad = data.child("ciudad").getValue(String.class);
                        String longAddress = direccion + "\n" + comuna + ", " + ciudad + ".";
                        mClientAddressTextView.setText(longAddress);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            mClientAddressDatabaseReference.addValueEventListener(mClientAddressValueEventListener);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }


    private void saveAllSalesArticles(String saleKey) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(this, getResources().getString(R.string.function_not_available), Toast.LENGTH_SHORT).show();
        } else if (id == R.id.action_search) {
            SearchView searchView = (SearchView) item.getActionView();
            searchView.setQueryHint(getResources().getString(R.string.search_articles_hint));

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    if (MathHelper.isNumeric(newText)) {
                        Query query = FirebaseDb.getArticlesCodeQuery(newText).limitToFirst(30);
                        mAdapter = getAdapter(query);
                    } else {
                        String text = newText.toUpperCase();
                        Query query = FirebaseDb.getArticlesDescriptionQuery(text);
                        mAdapter = getAdapter(query);
                    }
                    mAdapter.notifyDataSetChanged();
                    mArticlesListView.swapAdapter(mAdapter, false);
                    return false;
                }
            });
        }



        return super.onOptionsItemSelected(item);
    }

    private void writeNewSale() {
        Sale sale = new Sale(
                false, String.valueOf(System.currentTimeMillis()),
                mClientId,
                mClientAddressId,
                mUserId,
                mTotalPrice);
        DatabaseReference saleRef = FirebaseDb.sSalesRef.push();

        saleRef.setValue(sale);

        mSaleUid = saleRef.getKey();
    }

    private void addArticleSale(final DatabaseReference ref, final Article article) {
        ref.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                ArticleSale articleSale = mutableData.getValue(ArticleSale.class);
                if (articleSale == null) {
                    ref.setValue(new ArticleSale(1, Long.valueOf(article.getPrecio())));
                    return Transaction.success(mutableData);
                } else {
                    int amount = articleSale.getCantidad() + 1;
                    long total = amount * Long.valueOf(article.getPrecio());
                    articleSale = new ArticleSale(amount, total);
                }

                mutableData.setValue(articleSale);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {

            }
        });

    }


}
