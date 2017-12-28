package com.aveteam.lorienzo9.istudy.Pages;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.aveteam.lorienzo9.istudy.R;
import com.aveteam.lorienzo9.istudy.Types.Text;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by lorienzo9 on 08/12/17.
 */

public class AddPost extends Fragment {
    ArrayList<String> imageItems = new ArrayList<>();
    int images[] = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher
    };
    GridView gridView;
    StorageReference mRef;
    FirebaseAuth auth;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_post, container, false); //Manage Layout
        auth = FirebaseAuth.getInstance();
        mRef = FirebaseStorage.getInstance().getReference();
        gridView = (GridView)view.findViewById(R.id.grid_post_type);

        gridView.setAdapter(new Adapter(getActivity()));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getContext(), Text.class));
                //Avvia activity
            }
        });

        return view;
    }
    public class Adapter extends BaseAdapter{
        Context mContext;
        public Adapter (Context c ){
            mContext = c;
        }
        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView imageView;
            if (view == null){
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 32, 8, 32);
            } else {
                imageView = (ImageView) view;
            }
            imageView.setImageResource(images[i]);
            return imageView;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return images[i];
        }

    }
}
