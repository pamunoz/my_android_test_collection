package com.pfariasmunoz.firebaseuidatabasetutorial.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.pfariasmunoz.firebaseuidatabasetutorial.R;
import com.pfariasmunoz.firebaseuidatabasetutorial.data.models.Post;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment {

    private RecyclerView mPostRecyclerView;
    private FirebaseRecyclerAdapter<Post, PostViewHolder> mPostAdapter;
    private View mRootView;


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
        mPostRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        mPostRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        setUpAdapter();
    }

    private void setUpAdapter() {
        mPostAdapter = new FirebaseRecyclerAdapter<Post, PostViewHolder>(
                Post.class,
                R.layout.item_post,
                PostViewHolder.class,

        ) {
            @Override
            protected void populateViewHolder(PostViewHolder viewHolder, Post model, int position) {

            }
        }
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {


        public PostViewHolder(View itemView) {
            super(itemView);
        }
    }
}
