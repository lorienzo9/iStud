package com.aveteam.lorienzo9.istudy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lorienzo9 on 27/09/17.
 */

public class RecylerDayAdapter extends RecyclerView.Adapter<RecylerDayAdapter.ViewHolderDays> {

    Context context;
    ArrayList<String> list;
    public RecylerDayAdapter(Context c, ArrayList<String> arrayList){
        this.list = arrayList;
        this.context = c;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(ViewHolderDays holder, int position) {
        holder.day.setText(list.get(position));
    }

    @Override
    public ViewHolderDays onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.dayitem, parent, false);
        return new ViewHolderDays(v);
    }

    public class ViewHolderDays extends RecyclerView.ViewHolder{
        TextView day;

        public ViewHolderDays(View view){
            super(view);
            day = (TextView)view.findViewById(R.id.day_text);
        }

    }
}
