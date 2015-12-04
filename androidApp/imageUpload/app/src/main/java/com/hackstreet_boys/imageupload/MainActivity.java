package com.hackstreet_boys.imageupload;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Pair;
import android.view.View;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 1;
    private ImageView to_upload;
    private EditText image_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        to_upload = (ImageView) findViewById(R.id.imageView);
        image_name = (EditText) findViewById(R.id.editText);
    }
    public void upload(View v){
        Bitmap image = ((BitmapDrawable)to_upload.getDrawable()).getBitmap();
        new UploadImage(image, "201352029").execute();
    }

    public void select_image(View v){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selected_image = data.getData();
            to_upload.setImageURI(selected_image);
        }
    }
    private class UploadImage extends AsyncTask <Void, Void, Void>{
        Bitmap image;
        String name;
        public UploadImage(Bitmap image, String name){
            this.image = image;
            this.name = name;
        }
        @Override
        protected Void doInBackground(Void... params) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
            ArrayList<Pair<String, String>> data_to_send = new ArrayList<Pair<String, String>>();
            data_to_send.add(new Pair<String, String>("image", encodedImage));
            data_to_send.add(new Pair<String, String>("user_id", name));
            try {
                URL url = new URL("http://10.100.91.55/beyondbooks/andy_set_image.php");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                ArrayList<Pair<String, String>> to_send = data_to_send;
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getQuery(to_send));
                writer.flush();
                writer.close();
                System.out.println("Result1: ");
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                StringBuilder result = null;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
    private String getQuery(ArrayList<Pair<String, String>> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (int i=0;i<params.size();i++) {
            if (i!=0)
                result.append("&");

            result.append(URLEncoder.encode(params.get(i).first, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(params.get(i).second, "UTF-8"));
        }
        System.out.println("Result0: " + result.toString());
        return result.toString();
    }
}
