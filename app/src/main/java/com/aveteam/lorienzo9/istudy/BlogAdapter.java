package com.aveteam.lorienzo9.istudy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aveteam.lorienzo9.istudy.Constructors.Blog;

import java.util.ArrayList;

/**
 * Created by lorienzo9 on 08/11/17.
 */

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.BlogHolder> {
    ArrayList<Blog> list;
    Context context;
    int TYPE;
    public BlogAdapter(Context context, ArrayList<Blog> array){
        this.list = array;
        this.context = context;
    }
    @Override
    public BlogHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        /*if (viewType == 11){
            v = LayoutInflater.from(context).inflate(R.layout.text_format, parent, false);
        }else {
            v = null;
        }*/
        v = LayoutInflater.from(context).inflate(R.layout.text_format, parent, false);

        return new BlogHolder(v);
    }

    @Override
    public void onBindViewHolder(BlogHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.description.setText(list.get(position).getContent());
        holder.user.setText(list.get(position).getUser_id());
        holder.date.setText(list.get(position).getDate());
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getType().equals("text_format")){
            TYPE=11;
        }
        else if (list.get(position).getType().equals("poll_format")){

        } else {
            return 0;
        }
        return TYPE;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BlogHolder extends RecyclerView.ViewHolder{
        TextView title, description, user, date;
        public BlogHolder(View view){
            super(view);
            title = (TextView)view.findViewById(R.id.title_blog_post);
            description = (TextView)view.findViewById(R.id.description_blog_post);
            user = (TextView)view.findViewById(R.id.author);
            date = (TextView)view.findViewById(R.id.date_post);

        }
    }
}
