package com.pfariasmunoz.indensales.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pfariasmunoz.indensales.R;
import com.pfariasmunoz.indensales.data.models.Article;

/**
 * Created by Pablo Farias on 17-04-17.
 */

public class ArticleViewHolder extends RecyclerView.ViewHolder {
    TextView mDescriptionTextView;
    TextView mPriceTextView;

    public ArticleViewHolder(View itemView) {
        super(itemView);
        mDescriptionTextView = (TextView) itemView.findViewById(R.id.tv_article_description);
        mPriceTextView = (TextView) itemView.findViewById(R.id.tv_article_price);
    }

    public void setTextOnViews(Article article) {
        mDescriptionTextView.setText(article.getDescripcion());
        mPriceTextView.setText(article.getPrecio());
    }
}
