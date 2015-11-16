package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity  {

    private EditText username;
    private EditText password;
    private TextView aboutus;
    private TextView forgot_pass;
    Integer id;
    String password_string;
    public static UserData userData;
    private Button LogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        aboutus = (TextView) findViewById(R.id.aboutus);
        forgot_pass = (TextView) findViewById(R.id.textView2);
        LogIn = (Button)findViewById(R.id.login);
        LogIn.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        RequestServer rs = new RequestServer();
                        if(username.getText().toString().length() != 0) {
                            System.out.println("Entering here");
                            id = Integer.parseInt(username.getText().toString());
                        }
                        else {
                            System.out.println("Entering here 2");
                            id = 12345;
                        }
                        password_string = password.getText().toString();
                        System.out.println("Output: " + id);
                        boolean allowed = rs.authenticate(id, password_string);
                        if (allowed)
                        {
                            pass();
                        }
                        else
                            not_allowed();
                    }
                }
        );
        aboutus.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        about_us();
                    }
                }
        );
        forgot_pass.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        if(username.getText().toString().length() != 0) {
                            id = Integer.parseInt(username.getText().toString());
                            change_pass_request();
                        }
                        else{
                            change_pass_cancel();
                        }
                    }
                }
        );


    }

    public void pass(){
        MainActivity.userData = new UserData(id);
        Intent intent = new Intent(getApplicationContext(), Wireframe13.class);
        intent.putExtra("user_data", userData);
        Toast.makeText(this, "OK allowed", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    public void not_allowed(){
        Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_LONG).show();
    }

    public void about_us(){
        Intent au = new Intent(this,AboutUs.class);
        au.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(au);
    }
    public void change_pass_request(){
        userData = new UserData(id);
        RequestServer requestServer = new RequestServer();
        Boolean result = requestServer.authenticate_forget(userData.getId());
        if(result){
            Toast.makeText(this, "Password sent to mail", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Some error occured", Toast.LENGTH_LONG).show();
        }
    }
    public void change_pass_cancel(){
        Toast.makeText(this, "Enter your id first", Toast.LENGTH_LONG).show();
    }


}
