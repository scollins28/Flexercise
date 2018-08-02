package com.example.android;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ScrollView;

import com.example.android.free.R;

import java.util.ArrayList;

import github.nisrulz.recyclerviewhelper.RVHItemClickListener;
import github.nisrulz.recyclerviewhelper.RVHItemDividerDecoration;
import github.nisrulz.recyclerviewhelper.RVHItemTouchHelperCallback;

public class NewsFeed extends android.support.v4.app.Fragment {

    View rootView;
    ImageButton newsListBackButton;
    ArrayList<News> news;
    public Context mContext;
    Toolbar mToolbar;
    ScrollView scrollView;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mContext = getContext();

        news = HomeScreen.news;

        linearLayoutManager = new LinearLayoutManager( mContext, LinearLayoutManager.VERTICAL, false );

        if (news != null) {
            Log.e( "News is not null", String.valueOf( news ) );
            if (news.size() != 0) {
                Log.e( "News is not 0", String.valueOf( news ) );
                rootView = inflater.inflate( R.layout.news_feed_fragment, container, false );
                scrollView = rootView.findViewById( R.id.news_list_sv);

                recyclerView = rootView.findViewById( R.id.news_list_view );
                NewsListAdapter newsListAdapter = new NewsListAdapter( news, mContext );
                recyclerView.setAdapter( newsListAdapter );

                recyclerView.setLayoutManager( linearLayoutManager );
                recyclerView.setHasFixedSize( false );

                RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration( mContext, DividerItemDecoration.VERTICAL );
                ItemTouchHelper.Callback callback = new RVHItemTouchHelperCallback( newsListAdapter, true, true,
                        true );
                ItemTouchHelper helper = new ItemTouchHelper( callback );
                helper.attachToRecyclerView( recyclerView );

                // Set the divider in the recyclerview
                recyclerView.addItemDecoration( new RVHItemDividerDecoration( mContext, LinearLayoutManager.VERTICAL ) );

                // Set On Click Listener
                recyclerView.addOnItemTouchListener( new RVHItemClickListener( mContext, new RVHItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent( Intent.ACTION_VIEW );
                        intent.setData( Uri.parse( news.get( position ).mUrl ) );
                        mContext.startActivity( intent );
                    }
                } ) );

                ScrollView scrollView = rootView.findViewById( R.id.news_list_sv );
                scrollView.setVerticalScrollbarPosition( HomeScreen.listStateOne );

                newsListAdapter.notifyDataSetChanged();
                newsListBackButton = rootView.findViewById( R.id.news_list_back_button );

                View.OnClickListener listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = 0;
                        if (v == newsListBackButton) {
                            position = 0;
                            HomeScreen.listStateOne = 0;
                        }
                        mCallback.onNewsListButtonSelected( position );
                    }
                };

                //Attaching the click listener to the buttons

                newsListBackButton.setOnClickListener( listener );


                HomeScreen.checkDisplayBanner( rootView, HomeScreen.removeAdvertsValue );

            } else {
                rootView = inflater.inflate( R.layout.news_feed_fragment_no_internet, container, false );
                newsListBackButton = rootView.findViewById( R.id.news_list_back_button );
                View.OnClickListener listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = 0;
                        if (v == newsListBackButton) {
                            position = 0;
                            HomeScreen.listStateOne = 0;
                        }
                        mCallback.onNewsListButtonSelected( position );
                    }
                };

                //Attaching the click listener to the buttons

                newsListBackButton.setOnClickListener( listener );


                HomeScreen.checkDisplayBanner( rootView, HomeScreen.removeAdvertsValue );

            }


        }
        mToolbar = rootView.findViewById( R.id.toolbar );
        return rootView;
    }

    com.example.android.NewsFeed.onNewsListButtonSelected mCallback;


    //Interface for the click listener to send which button has been pushed to the home screen, and activate the fragment transaction
    public interface onNewsListButtonSelected {
        void onNewsListButtonSelected(int position);
    }

    //Attaching the interface to the MasterListFragment
    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        try {
            mCallback = (com.example.android.NewsFeed.onNewsListButtonSelected) context;
        } catch (ClassCastException e) {
            throw new ClassCastException( context.toString() + "must implement click listener" );
        }
    }

    @Override
    public void onViewStateRestored(@Nullable final Bundle savedInstanceState) {
        super.onViewStateRestored( savedInstanceState );
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey( "scrollViewNews" )) {
                int sv = savedInstanceState.getInt( "scrollViewNews" );
                scrollView.scrollTo( 0, sv );
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState( outState );
        int sv = scrollView.getScrollY();
        outState.putInt( "scrollViewNews" , sv);
    }

}
