package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {
    EditText pass1 , pass2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        pass1 = (EditText)findViewById(R.id.editText);
        pass2 = (EditText)findViewById(R.id.editText2);
    }
    public void set_new_password(View v){
        String pas1 = pass1.getText().toString();
        String pas2 = pass2.getText().toString();
        if(pas1.equals(pas2) && pas1.length() != 0){
                RequestServer requestServer = new RequestServer();
                Boolean result = requestServer.set_new_password(MainActivity.userData.getId(), pas1);
                if(result){
                    Toast.makeText(this, "New password set successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, Wireframe8.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(this, "Some error occured", Toast.LENGTH_LONG).show();
                }
        }
        else{
            Toast.makeText(this, "Set some password", Toast.LENGTH_LONG).show();
        }
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
                in = new Intent(this,Search.class);
                startActivity(in);
                break;
            case R.id.option_home:

                in = new Intent(this, BookViewPage.class);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
            case R.id.option_sell:
                in = new Intent(this,SellBook.class);
                startActivity(in);
                break;
            case R.id.option_sell_list:
                in = new Intent(this,SellingList.class);
                startActivity(in);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
