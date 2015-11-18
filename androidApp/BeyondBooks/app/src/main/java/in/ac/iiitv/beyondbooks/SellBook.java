package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SellBook extends AppCompatActivity {
    private EditText isbn;
    private String book_name = null;
    private Button identify,sellbook;
    private boolean isidentified=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_book);
        isbn = (EditText) findViewById(R.id.sellbook_isbn);

        identify = (Button)findViewById(R.id.sellbook_identify);
        identify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isidentified = identify(v);
            }
        });

        //add book to sell
        sellbook = (Button) findViewById(R.id.sell_book);
        if(isidentified){
            EditText bookdesc = (EditText) findViewById(R.id.book_desc);
            EditText age = (EditText) findViewById(R.id.book_age);
            EditText price = (EditText) findViewById(R.id.book_price);

            sellbook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sell(v);
                }
            });
        }
    }


    public boolean identify(View v){
        boolean identified = false;
        Long to_send = Long.parseLong(isbn.getText().toString());
        RequestServer requestServer = new RequestServer();
        book_name = requestServer.identify_book(to_send);
        if(book_name != null){
            TextView textView = (TextView) findViewById(R.id.sellbook_identified);
            textView.setText(book_name);
            identified = true;
        }
        else{
            identified = false;
            Toast.makeText(this, "Incorrect isbn entered", Toast.LENGTH_LONG).show();
        }
        return identified;
    }
    public void sell(View view){
        if(book_name == null){
            Toast.makeText(this, "Book not identified", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Book added in the selling list", Toast.LENGTH_LONG).show();
            Intent intent = getIntent();
            UserData userData = (UserData)intent.getSerializableExtra("user_data");
            intent = new Intent(this, BookViewPage.class);
            intent.putExtra("user_data", userData);
            startActivity(intent);
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
            case R.id.option_logout:
                in = new Intent(this,MainActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
