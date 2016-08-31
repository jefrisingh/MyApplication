package com.example.jefri.myapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerViewTest extends AppCompatActivity implements RecyclerViewClicked{

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_test);
        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        new getResponse().execute();
    }

    @Override
    public void OnClick(UserDetails userDetails) {
        getSupportActionBar().setTitle(userDetails.getCountry());
    }

    class getResponse extends AsyncTask<Void,Void,Boolean>{

        ProgressDialog progress;
        ArrayList<UserDetails>  userDetails;
        @Override
        protected Boolean doInBackground(Void... params) {
            String response = new PostGetInteraction().GetResponse("http://www.w3schools.com/website/Customers_MYSQL.php");
            if (response != null && response.length()>0){
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject values = jsonArray.getJSONObject(i);
                        String Name = values.getString("Name");
                        String City = values.getString("City");
                        String Country = values.getString("Country");
                        userDetails.add(new UserDetails(Name,City,Country));
                    }
                    return true;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            return false;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            userDetails = new ArrayList<>();
            progress = new ProgressDialog(RecyclerViewTest.this);
            progress.setTitle("Please wait...");
            progress.setMessage("communicating");
            progress.show();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            progress.dismiss();

            if (aBoolean){
                recyclerView.setAdapter(new RecyclerAdapter(userDetails,RecyclerViewTest.this));
            }
            else {
                Toast.makeText(RecyclerViewTest.this,"something went wrong",Toast.LENGTH_LONG).show();
            }
        }
    }
}
