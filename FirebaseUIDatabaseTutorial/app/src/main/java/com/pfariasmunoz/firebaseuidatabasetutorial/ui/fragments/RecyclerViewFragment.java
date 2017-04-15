package com.pfariasmunoz.firebaseuidatabasetutorial.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pfariasmunoz.firebaseuidatabasetutorial.R;
import com.pfariasmunoz.firebaseuidatabasetutorial.data.models.Post;
import com.pfariasmunoz.firebaseuidatabasetutorial.utils.Constants;
import com.pfariasmunoz.firebaseuidatabasetutorial.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment {

    private RecyclerView mPostRecyclerView;
    private FirebaseRecyclerAdapter<Post, PostViewHolder> mPostAdapter;
    private View mRootView;
    private DatabaseReference mPostDatabaseReference;


    public RecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        initializeView();

        return mRootView;
    }

    private void initializeView() {
        mPostDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.TABLE_POST);
        mPostRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        mPostRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        setUpAdapter();
    }

    private void setUpAdapter() {
        mPostAdapter = new FirebaseRecyclerAdapter<Post, PostViewHolder>(
                Post.class,
                R.layout.item_post,
                PostViewHolder.class,
                mPostDatabaseReference) {
            @Override
            protected void populateViewHolder(PostViewHolder viewHolder, final Post model, int position) {
                viewHolder.setPostText(model.getText());
                viewHolder.favouriteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int randomEmailIndex = (int) (Math.random() * Constants.EMAIL_LIST.length);
                        FirebaseDatabase.getInstance().getReference(Constants.TABLE_FAVOURITES)
                                .child(Constants.EMAIL_LIST[randomEmailIndex])
                                .child(model.getUID())
                                .setValue(true);
                    }
                });
            }
        };
    }

    private void addFloatingActionButton() {
        int fabId = R.id.fab;
        FloatingActionButton fab = (FloatingActionButton) mRootView.findViewById(fabId);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post post = new Post();
                post.setUID(Utils.getPostUID());
                post.setText("Random text: " + post.getUID());

                mPostDatabaseReference.child(post.getUID()).setValue(post);

            }
        });
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {

        Button favouriteButton;
        TextView postTextView;

        public PostViewHolder(View itemView) {
            super(itemView);

            favouriteButton = (Button) itemView.findViewById(R.id.bt_favourite);
            postTextView = (TextView) itemView.findViewById(R.id.tv_post);
        }

        private void setPostText(String postText) {
            postTextView.setText(postText);
        }
    }
}
