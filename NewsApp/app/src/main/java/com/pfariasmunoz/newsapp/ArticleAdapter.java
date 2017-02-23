package com.pfariasmunoz.newsapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pablo Farias on 25-01-17.
 */

public class ArticleAdapter extends ArrayAdapter<Article> {

    public ArticleAdapter(Activity context, ArrayList<Article> articles) {
        super(context, 0, articles);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // set the text title
        TextView titleTextView = (TextView)listItemView.findViewById(R.id.title);
        TextView authorTextView = (TextView) listItemView.findViewById(R.id.author);
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        TextView sectionTextView = (TextView) listItemView.findViewById(R.id.section);

        // Find the article at the given position in the list of articles.
        Article currentArticle = getItem(position);

        String title = currentArticle.getTitle();
        titleTextView.setText(title);
        String author = currentArticle.getAuthorName();
        authorTextView.setText(author);
        String date = currentArticle.getDateOfPublication();
        dateTextView.setText(date);
        String section = currentArticle.getSection();
        sectionTextView.setText(section);


        return listItemView;
    }
}
