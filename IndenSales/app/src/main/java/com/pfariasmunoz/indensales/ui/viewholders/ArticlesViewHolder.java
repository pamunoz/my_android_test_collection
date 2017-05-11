package com.pfariasmunoz.indensales.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.pfariasmunoz.indensales.R;
import com.pfariasmunoz.indensales.data.FirebaseDb;
import com.pfariasmunoz.indensales.data.models.Article;
import com.pfariasmunoz.indensales.data.models.ArticleSale;
import com.pfariasmunoz.indensales.data.models.Sale;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Pablo Farias on 05-05-17.
 */

public class ArticlesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public static final String TAG = ArticlesViewHolder.class.getSimpleName();

    @BindView(R.id.tv_article_description)
    TextView mArticleDescriptionTextView;
    @BindView(R.id.tv_article_price)
    TextView mArticlePriceTextView;
    @BindView(R.id.tv_article_amount)
    TextView mArticleAmountTextView;
    @BindView(R.id.tv_article_total_price)
    TextView mArticleTotalPriceTextView;
    @BindView(R.id.imb_up_arrow)
    ImageButton mAddArticleToSaleButton;
    @BindView(R.id.imb_down_arrow)
    ImageButton mSubtractArticleFromSalesButton;


    public ArticlesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        mAddArticleToSaleButton.setOnClickListener(this);
        mSubtractArticleFromSalesButton.setOnClickListener(this);
    }

    public ImageButton getAddArticleToSaleButton() {
        return mAddArticleToSaleButton;
    }

    public ImageButton getSubtractArticleFromSalesButton() {
        return mSubtractArticleFromSalesButton;
    }


    public void setArticleAmount(String amount) {
        Log.i(TAG, "AMOUNT SETTED: " + amount);
        Log.i(TAG, "ITEM: " + String.valueOf(getItemId()));
        mArticleAmountTextView.setText(amount);
    }

    public void setArticleTotalPrice(String price) {
        Log.i(TAG, "AMOUNT SETTED: " + price);
        Log.i(TAG, "ITEM: " + String.valueOf(getItemId()));
        mArticleTotalPriceTextView.setText(price);
    }

    public void setDescriptionAndPrice(Article article) {
        mArticleDescriptionTextView.setText(article.getDescripcion());
        mArticlePriceTextView.setText(article.getPrecio());
    }

    public TextView getArticleAmountTextView() {
        return mArticleAmountTextView;
    }

    public TextView getArticleTotalPriceTextView() {
        return mArticleTotalPriceTextView;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.imb_up_arrow:
                // Do something
                break;
            case R.id.imb_down_arrow:
                // Do something else.
                break;

        }

    }
}
