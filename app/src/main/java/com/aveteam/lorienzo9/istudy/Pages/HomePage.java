package com.aveteam.lorienzo9.istudy.Pages;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.aveteam.lorienzo9.istudy.AppController;
import com.aveteam.lorienzo9.istudy.Homeworks;
import com.aveteam.lorienzo9.istudy.R;
import com.aveteam.lorienzo9.istudy.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by lorienzo9 on 23/09/17.
 */

public class HomePage extends Fragment{
    private RecyclerView recyclerView;
    private CalendarView calendarView;
    Calendar date;
    int datetry;
    RecyclerViewAdapter adapter;
    private ArrayList<Homeworks> list = new ArrayList<>();

    final static String URL = "http://aveteamdev.altervista.org/AveProject/exercise.json";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_collapsed, container, false);


        calendarView = (CalendarView) view.findViewById(R.id.calendar);
        calendarView.setFirstDayOfWeek(2);

        adapter = new RecyclerViewAdapter(getContext(), list);

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_collapsed);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

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
            return "Done";
        }
    }
}
