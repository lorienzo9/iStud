package com.aveteam.lorienzo9.istudy;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aveteam.lorienzo9.istudy.Constructors.Days;

import java.util.ArrayList;

/**
 * Created by lorienzo9 on 27/09/17.
 */

public class RecylerDayAdapter extends RecyclerView.Adapter<RecylerDayAdapter.ViewHolderDays> {

    int selectedposition;
    Context context;
    ArrayList<Days> list;
    public RecylerDayAdapter(Context c, ArrayList<Days> arrayList){
        this.list = arrayList;
        this.context = c;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(ViewHolderDays holder, int position) {
        holder.day.setText(list.get(position).getDay());
    }

    @Override
    public ViewHolderDays onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.dayitem, parent, false);
        return new ViewHolderDays(v);
    }

    public class ViewHolderDays extends RecyclerView.ViewHolder{
        TextView day;
        ConstraintLayout layout;

        public ViewHolderDays(View view){
            super(view);
            layout = (ConstraintLayout)view.findViewById(R.id.layout);
            day = (TextView)view.findViewById(R.id.day_text);
        }

    }
}
