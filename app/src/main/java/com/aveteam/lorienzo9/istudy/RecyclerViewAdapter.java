package com.aveteam.lorienzo9.istudy;

import android.content.Context;
import android.support.constraint.solver.SolverVariable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lorienzo9 on 20/09/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    int materia;
    int color;
    ArrayList<Homeworks> list = new ArrayList<>();
    public RecyclerViewAdapter(Context context, ArrayList<Homeworks> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.descrizione.setText(list.get(position).getDescrizione());
        holder.titolo.setText(list.get(position).getTitolo());
        materia = list.get(position).getTAG();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titolo, descrizione;
        ImageView dot;
        EditText editText;
        boolean isFocusable = false;
        View v;
        public ViewHolder(View view){
            super(view);
            editText = (EditText)view.findViewById(R.id.editText2);
            titolo = (TextView)view.findViewById(R.id.titolo);
            descrizione = (TextView)view.findViewById(R.id.Text_info);
            dot = (ImageView)view.findViewById(R.id.imageView2);
        }
    }
}

