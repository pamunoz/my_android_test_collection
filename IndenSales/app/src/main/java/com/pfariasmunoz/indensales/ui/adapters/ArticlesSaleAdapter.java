package com.pfariasmunoz.indensales.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.pfariasmunoz.indensales.R;
import com.pfariasmunoz.indensales.data.models.Article;
import com.pfariasmunoz.indensales.data.models.ArticleSale;
import com.pfariasmunoz.indensales.data.models.SaleData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlesSaleAdapter extends RecyclerView.Adapter<ArticlesSaleAdapter.Holder> {

    private List<SaleData> mSaleDataList;
    private Context mContext;
    private ButtonClickListener mButtonClickListener;

    public interface ButtonClickListener {
        void onAddButtonClick(SaleData saleData);
        void onSubtractButtonClick(SaleData saleData);
    }

    public ArticlesSaleAdapter(Context context, List<SaleData> saleDataList) {
        mSaleDataList = saleDataList;
        mContext = context;
    }

    @Override
    public ArticlesSaleAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.item_article;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        ArticlesSaleAdapter.Holder viewHolder = new ArticlesSaleAdapter.Holder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ArticlesSaleAdapter.Holder holder, int position) {

        SaleData saleData = mSaleDataList.get(position);
        if (saleData.getArticleSale() != null) {
            holder.setArticleTotalPrice(String.valueOf(saleData.getArticleSale().getTotal()));
            holder.setArticleAmount(String.valueOf(saleData.getArticleSale().getCantidad()));
        } else {
            holder.setArticleTotalPrice("$0");
            holder.setArticleAmount("0");
        }

        if (saleData.getArticle() != null) {
            holder.setDescriptionAndPrice(saleData.getArticle());
        }

    }

    @Override
    public int getItemCount() {
        return mSaleDataList.size();
    }

    private Context getContext() {
        return mContext;
    }



    // The view Holder
    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        final String TAG = com.pfariasmunoz.indensales.ui.viewholders.ArticlesViewHolder.class.getSimpleName();

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


        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mAddArticleToSaleButton.setOnClickListener(this);
            mSubtractArticleFromSalesButton.setOnClickListener(this);
        }

        public void setArticleAmount(String amount) {
            mArticleAmountTextView.setText(amount);
        }

        public void setArticleTotalPrice(String price) {
            mArticleTotalPriceTextView.setText(price);
        }

        public void setDescriptionAndPrice(Article article) {
            String description = article != null ? article.getDescripcion() : "No Description";
            String price = article != null ? article.getPrecio() : "No Price";
            mArticleDescriptionTextView.setText(description);
            mArticlePriceTextView.setText(price);
        }

        @Override
        public void onClick(View v) {
            int clicketPosition = getAdapterPosition();
            switch (v.getId()) {

                case R.id.imb_up_arrow:
                    // Do something
                    mButtonClickListener
                            .onAddButtonClick(
                                    mSaleDataList.get(clicketPosition));
                    break;
                case R.id.imb_down_arrow:
                    // Do something else.
                    mButtonClickListener
                            .onSubtractButtonClick(
                                    mSaleDataList.get(clicketPosition));
                    break;
            }

        }
    }

}
