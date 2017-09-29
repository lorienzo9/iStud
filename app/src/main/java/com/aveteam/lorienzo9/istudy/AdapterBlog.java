package com.aveteam.lorienzo9.istudy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aveteam.lorienzo9.istudy.Constructors.Blog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lorienzo9 on 29/09/17.
 */

public class AdapterBlog extends RecyclerView.Adapter<AdapterBlog.ViewHolderBlog> {
    Context context;
    ArrayList<Blog> list;

    public  AdapterBlog(Context c, ArrayList<Blog> list){
        this.list = list;
        this.context = c;
    }
    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onBindViewHolder(ViewHolderBlog holder, int position) {

    }

    @Override
    public ViewHolderBlog onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dayitem, parent, false); //Cambiare il layout
        return new ViewHolderBlog(view);
    }

    public class ViewHolderBlog extends RecyclerView.ViewHolder{
        public ViewHolderBlog (View view){
            super(view);
        }
    }
    //Qui va aggiunta tanta roba LOL
}
