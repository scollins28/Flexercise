package com.example.android;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.free.R;

import java.util.ArrayList;
import java.util.Collections;

import github.nisrulz.recyclerviewhelper.RVHAdapter;
import github.nisrulz.recyclerviewhelper.RVHViewHolder;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsHolder> implements RVHAdapter {

    private Context mContext;
    private ArrayList<News> news;

    public NewsListAdapter(ArrayList<News>news, Context context) {
        this.news = news;
        mContext = context;
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        swap(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position, int direction) {
        remove(position);
    }

    public class NewsHolder extends RecyclerView.ViewHolder implements RVHViewHolder{
        public TextView newsName;
        public TextView newsDescription;
        public String newsUrl;
        private Context mContext;

        public NewsHolder(View view){
            super(view);
            newsName = view.findViewById( R.id.news_list_item_name);
            newsDescription = view.findViewById( R.id.news_list_description);
            mContext = view.getContext();
        }

        public void bindNews (News news){
            News mNews = news;
            newsName.setText( mNews.getNewsName() );
            if (!mNews.getDescription().equals( "" )) {
                newsDescription.setText( mNews.getDescription() );
            }
            newsUrl = mNews.getUrl();
        }

        @Override
        public void onItemSelected(int actionstate) {
            System.out.println("Item is selected");
        }

        @Override
        public void onItemClear() {
            System.out.println("Item is unselected");
        }

    }


    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.news_list_item, parent, false );
        NewsHolder newsHolder = new NewsHolder( itemView );
        return newsHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
      holder.bindNews ( news.get( position ) );
        }

    @Override
    public int getItemCount() {
        return news.size();
    }

    // Helper functions you might want to implement to make changes in the list as an event is fired
    private void remove(int position) {
        }

    private void swap(int firstPosition, int secondPosition) {
    }
}

