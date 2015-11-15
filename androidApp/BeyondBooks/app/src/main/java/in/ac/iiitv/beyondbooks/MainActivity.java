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
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //to check the different activity
        startActivity(new Intent(this,Frame5.class));


        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
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
            case R.id.search:
                in = new Intent(this,Frame5.class);

                startActivity(in);
                break;
            case R.id.home:

                in = new Intent(this, MainActivity.class);
                startActivity(in);
                break;
            case R.id.user_profile:
                in = new Intent(this,Wireframe8.class);
                startActivity(in);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        RequestServer rs = new RequestServer();
        boolean allowed = rs.authenticate(Integer.parseInt(username.getText().toString()),password.getText().toString());
        UserData userData = new UserData(Integer.parseInt(username.toString()));
        if (allowed)
        {
            Intent intent = new Intent(this, BookViewPage.class);
            intent.putExtra("user_data", userData);
            startActivity(intent);
        }
        else
            Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_LONG).show();
    }
}
