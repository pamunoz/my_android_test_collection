package com.pfariasmunoz.indensales.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.pfariasmunoz.indensales.R;
import com.pfariasmunoz.indensales.data.FirebaseDb;
import com.pfariasmunoz.indensales.data.models.Article;
import com.pfariasmunoz.indensales.ui.viewholders.ArticleViewHolder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticlesFragment extends Fragment {

    private RecyclerView mArticlesRecyclerView;
    private FirebaseRecyclerAdapter<Article, ArticleViewHolder> mArticleAdapter;
    private View mRootView;

    public ArticlesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_articles, container, false);
        initializeViews();
        return mRootView;
    }

    private void initializeViews() {
        mArticlesRecyclerView = (RecyclerView) mRootView.findViewById(R.id.rv_articles);
        mArticlesRecyclerView.setHasFixedSize(false);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        mArticlesRecyclerView.setLayoutManager(layoutManager);
        setupAdapter();
    }

    private void setupAdapter() {
        mArticleAdapter = new FirebaseRecyclerAdapter<Article, ArticleViewHolder>(
                Article.class,
                R.layout.item_article,
                ArticleViewHolder.class,
                FirebaseDb.sArticlesRef) {
            @Override
            protected void populateViewHolder(ArticleViewHolder viewHolder, Article model, int position) {
                viewHolder.setTextOnViews(model);
            }
        };
        mArticlesRecyclerView.setAdapter(mArticleAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mArticleAdapter != null) mArticleAdapter.cleanup();
    }
}
