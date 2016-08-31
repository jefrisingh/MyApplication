package com.example.jefri.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageLoadingActivity extends AppCompatActivity {

    ImageView urlImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlImage = (ImageView) findViewById(R.id.url_image);
        Picasso.with(ImageLoadingActivity.this).load("http://square.github.io/picasso/static/sample.png").into(urlImage);

        new DownloadImageTask().execute("http://www.vogella.com/tutorials/AndroidFragments/images/xretainedfragmentlifecyle10.png.pagespeed.ic.7-q2ddz2ze.png"
        ,"http://1.bp.blogspot.com/--u6IOODZGfE/UNHf2ctGC4I/AAAAAAAAC8s/9feUHSeuBa8/s640/AsyncTaskLifecycle.jpg",
                "http://2.bp.blogspot.com/-7LBf-Oh1KZk/UYKP3EqZ_aI/AAAAAAAAH3U/lE3O8TlXdHw/s1600/activity_lifecycle_02.jpg"
        ,"https://freelancersnepal.files.wordpress.com/2014/01/ee423-lifecycleflow.jpg"
        ,"http://image.slidesharecdn.com/asynctasks-100901205325-phpapp01/95/asynctasks-7-728.jpg?cb=1283374520");
    }

    class DownloadImageTask extends AsyncTask<String,Bitmap,Boolean>{

        @Override
        protected Boolean doInBackground(String... params) {

            for (int i = 0; i < params.length; i++) {
                URL url = null;
                try {
                    url = new URL(params[i]);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection connection  = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream inputStream  = connection.getInputStream();
                    publishProgress(BitmapFactory.decodeStream(inputStream));
                    Thread.sleep(2000);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean status) {
            super.onPostExecute(status);
            if (status){
                Toast.makeText(ImageLoadingActivity.this, "All Images Loaded Successfully", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onProgressUpdate(Bitmap... values) {
            super.onProgressUpdate(values);
            Bitmap bitmap = values[0];
            if (bitmap != null){
                urlImage.setImageBitmap(bitmap);
            }
        }
    }
}
