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
    UserData userData;
    private Button LogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent in=new Intent(this,Wireframe13.class);
        in.putExtra("user_data",userData);
        startActivity(in);


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
        userData = new UserData(id);
        Intent intent = new Intent(getApplicationContext(), Wireframe8.class);
        intent.putExtra("user_data", userData);
        Toast.makeText(this, "Correct details", Toast.LENGTH_LONG).show();
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Intent in;
        switch(id)
        {
            case R.id.option_search:
                in = new Intent(this,Frame5.class);
                startActivity(in);
                break;
            case R.id.option_home:

                in = new Intent(this, BookViewPage.class);
                startActivity(in);
                break;
            case R.id.option_user_profile:
                in = new Intent(this, Wireframe8.class);
                startActivity(in);
                break;
            case R.id.option_activity_on_forum:
                in = new Intent(this,Wireframe12.class);
                startActivity(in);
                break;
            case R.id.option_book_shelf:
                in = new Intent(this,Frame10.class);
                startActivity(in);
                break;
            case R.id.option_forum:
                in = new Intent(this,Wireframe13.class);
                startActivity(in);
                break;
            case R.id.option_reviewed_books:
                in = new Intent(this,Frame11.class);
                startActivity(in);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId())
//        {
//            case R.id.login:
//                System.out.println("Entering here");
//                RequestServer rs = new RequestServer();
//                boolean allowed = rs.authenticate(Integer.parseInt(username.getText().toString()),password.getText().toString());
//                System.out.println("Data sent to server");
//                UserData userData = new UserData(Integer.parseInt(username.toString()));
//                if (allowed)
//                {
//                    Intent intent = new Intent(this, BookViewPage.class);
//                    intent.putExtra("user_data", userData);
//                    startActivity(intent);
//                }
//                else
//                    Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.aboutus:
//                Intent au = new Intent(this,AboutUs.class);
//                au.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(au);
//                break;
//        }
//    }
}
