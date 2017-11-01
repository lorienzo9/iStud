package com.aveteam.lorienzo9.istudy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aveteam.lorienzo9.istudy.Constructors.Chat;
import com.aveteam.lorienzo9.istudy.Constructors.Users;

import java.util.ArrayList;

/**
 * Created by lorienzo9 on 01/11/17.
 */

public class AdapterChat extends RecyclerView.Adapter<AdapterChat.ViewHolderChat> {
    int USER = 69;
    String userID;
    Context context;
    ArrayList<Chat> list;

    public AdapterChat(String userID, Context context, ArrayList<Chat> list){
        this.list = list;
        this. userID = userID;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(ViewHolderChat holder, int position) {
        holder.text.setText(list.get(position).getText());
        holder.name.setText(list.get(position).getUser());
    }

    @Override
    public ViewHolderChat onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == USER){ //Layout Self message
            view = LayoutInflater.from(context).inflate(R.layout.chat_message, parent, false);
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.chat_message, parent, false); //Change your layout here
        }
        return new ViewHolderChat(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getUser().equals(userID)){
            return USER;
        }else {
         return 0;
        }
    }

    public class ViewHolderChat extends RecyclerView.ViewHolder{
        TextView text, name;
        public ViewHolderChat(View view){
            super(view);
            text = (TextView)view.findViewById(R.id.text_chat);
            name = (TextView)view.findViewById(R.id.name_surname);
        }
    }
}

