package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity implements LoaderInterface, View.OnClickListener {

    private EditText username;
    private EditText passwordet;
    private TextView aboutus;
    private TextView forgot_pass;
    Integer id;
    String password_string;
    public static UserData userData;
    private Button LogIn;
    private ProgressBar pb;
    private LinearLayout linlaHeaderProgress;

    @Override
    public void preDataRecv() {
        linlaHeaderProgress.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        linlaHeaderProgress = (LinearLayout) findViewById(R.id.linlaHeaderProgress);
        username = (EditText)findViewById(R.id.username);
        passwordet = (EditText)findViewById(R.id.password);
        aboutus = (TextView) findViewById(R.id.aboutus);
        forgot_pass = (TextView) findViewById(R.id.textView2);
        LogIn = (Button)findViewById(R.id.login);
        LogIn.setOnClickListener(this);

        aboutus.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        about_us();
                    }
                }
        );
        forgot_pass.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (username.getText().toString().length() != 0) {
                            id = Integer.parseInt(username.getText().toString());
                            change_pass_request();
                        } else {
                            change_pass_cancel();
                        }
                    }
                }
        );


    }

    public void pass(){

        MainActivity.userData = new UserData(id);
        passwordet.setText("");

        //Intent intent = new Intent(this, BookViewPage.class);
//        intent.putExtra("user_data", userData);
//        intent.putExtra("isbn", "1001");
        Toast.makeText(this, "Correct details", Toast.LENGTH_LONG).show();
//        startActivity(intent);
    }

    public void not_allowed(){
        Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_LONG).show();
    }

    public void about_us(){
//        Intent au = new Intent(this,AboutUs.class);
//        au.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(au);
    }
    public void change_pass_request(){
        userData = new UserData(id);
        RequestServer requestServer = new RequestServer(this);
//        requestServer.authenticate_forget(userData.getId());
        Toast.makeText(this,"A new password has been mailed. Please do check your spam!",Toast.LENGTH_LONG).show();
    }

    public void change_pass_cancel(){
        Toast.makeText(this, "Enter your id first", Toast.LENGTH_LONG).show();
    }



    @Override
    public void onClick(View v) {

        RequestServer rs = new RequestServer(this);
        if(username.getText().toString().length() != 0) {
            System.out.println("Entering here");
            id = Integer.parseInt(username.getText().toString());
        }
        else {
            Toast.makeText(this, "Come on enter something! Its easy", Toast.LENGTH_SHORT).show();
        }

        password_string = passwordet.getText().toString();

        new RequestServer(this).new Authenticate(id,password_string,this).execute();

    }


    @Override
    public void postDataRecv(Object robj) {
        if ((Boolean)robj)
        {
            pass();
        }
        else
            not_allowed();

        linlaHeaderProgress.setVisibility(View.GONE);

    }
}

