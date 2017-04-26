package com.pfariasmunoz.indensales.ui.viewholders;

import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.pfariasmunoz.indensales.R;
import com.pfariasmunoz.indensales.data.models.Article;

/**
 * Created by Pablo Farias on 17-04-17.
 */

public class ArticleViewHolder extends RecyclerView.ViewHolder {
    private TextView mDescriptionTextView;
    private TextView mPriceTextView;
    private TextView mAmountTextView;
    private ImageButton mAddButton;
    private ImageButton mSubstractButton;


    public ArticleViewHolder(View itemView) {
        super(itemView);
        mDescriptionTextView = (TextView) itemView.findViewById(R.id.tv_article_description);
        mPriceTextView = (TextView) itemView.findViewById(R.id.tv_article_price);
        mAmountTextView = (TextView) itemView.findViewById(R.id.tv_article_amount);
        mAddButton = (ImageButton) itemView.findViewById(R.id.imb_up_arrow);
        mSubstractButton = (ImageButton) itemView.findViewById(R.id.imb_down_arrow);
    }

    public void setTextOnViews(Article article) {
        mDescriptionTextView.setText(article.getDescripcion());
        mPriceTextView.setText(article.getPrecio());
    }

    public void setAmount(int amount) {
        String stringAmount = String.valueOf(amount);
        mAmountTextView.setText(stringAmount);
    }

    public ImageButton getAddButton() {
        return mAddButton;
    }

    public ImageButton getSubstractButton() {
        return mSubstractButton;
    }
}
