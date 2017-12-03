package com.aveteam.lorienzo9.istudy.Pages;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.aveteam.lorienzo9.istudy.AppController;
import com.aveteam.lorienzo9.istudy.Constructors.Days;
import com.aveteam.lorienzo9.istudy.Homeworks;
import com.aveteam.lorienzo9.istudy.OnItemClickListener;
import com.aveteam.lorienzo9.istudy.R;
import com.aveteam.lorienzo9.istudy.RecyclerViewAdapter;
import com.aveteam.lorienzo9.istudy.RecylerDayAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by lorienzo9 on 23/09/17.
 */

public class HomePage extends Fragment{
    private RecyclerView recyclerView, days;
    private CalendarView calendarView;
    Calendar date;
    int datetry;
    RecyclerViewAdapter adapter;
    ArrayList<Days> listday = new ArrayList<>();
    private ArrayList<Homeworks> list = new ArrayList<>();
    private Toolbar toolbar;
    RecylerDayAdapter adapterday;
    private StorageReference storageReference;
    private File localFile;

    final static String URL = "http://aveteamdev.altervista.org/AveProject/exercise.json";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_collappsed_3days, container, false);



        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        adapter = new RecyclerViewAdapter(getContext(), list);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_collapsed);
        days = (RecyclerView)view.findViewById(R.id.recycler3days);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        days.setLayoutManager(new LinearLayoutManager(getContext()));
        days.setHasFixedSize(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        listday.add(new Days(day(0), true, R.color.colorAccent));
        listday.add(new Days(day(1), false, R.color.colorPrimary));
        listday.add(new Days(day(2), false, R.color.colorPrimary));
        adapterday  = new RecylerDayAdapter(getActivity(), listday);
        days.setAdapter(adapterday);
        days.addOnItemTouchListener(new OnItemClickListener(getContext(), days, new OnItemClickListener.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                adapterday.notifyDataSetChanged();
                //Aggiungere stringa da cui recuperare i file
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

        new LoadRecycler().execute();

        return view;
    }
    public String day(int numb){
        date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_MONTH, numb);
        datetry = date.get(Calendar.DATE);
        return String.valueOf(datetry)+" / "+String.valueOf(date.get(Calendar.MONTH)+1);
    }
    public void fetchRecycler(){
        JsonArrayRequest request = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        Homeworks homeworks = new Homeworks();
                        JSONObject object = response.getJSONObject(i);

                        JSONObject ex = object.getJSONObject("exercises");
                        homeworks.setTitolo(ex.getString("title"));
                        homeworks.setDescrizione(ex.getString("description"));
                        homeworks.setTAG(Integer.valueOf(ex.getString("tag")));

                        list.add(homeworks);
                        adapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(request);
    }
    private class LoadRecycler extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... strings) {
            fetchRecycler();
            //ManageFile();
            return "Done";
        }
    }
    public void deselectList(){
        listday.get(0).setSelected(false);
        listday.get(1).setSelected(false);
        listday.get(2).setSelected(false);
    }
    public void fetchRecyclerFireBase(){
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray Array = obj.getJSONArray("homeworks");

            for (int i = 0; i < Array.length(); i++) {
                Homeworks homeworks = new Homeworks();
                JSONObject ex = Array.getJSONObject(i);

                //JSONObject ex = object.getJSONObject("exercises");
                homeworks.setTitolo(ex.getString("title"));
                homeworks.setDescrizione(ex.getString("description"));
                homeworks.setTAG(Integer.valueOf(ex.getString("tag")));

                list.add(homeworks);
                adapter.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public String loadJSONFromAsset(){
        String json = null;
        try {
            InputStream is = new FileInputStream(localFile);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    public void ManageFile(){
        StorageReference islandRef = storageReference.child("gs://istudy-8e9ec.appspot.com/classe1/homeworks/jsonhome.json");
            try {
                localFile = File.createTempFile("jsonhome", "json");
                islandRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        fetchRecyclerFireBase();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                    }
                });
            } catch (IOException e){
                e.printStackTrace();
            }
    }
}
