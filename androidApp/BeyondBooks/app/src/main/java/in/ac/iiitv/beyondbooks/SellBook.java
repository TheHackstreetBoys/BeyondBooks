package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SellBook extends AppCompatActivity {
    private EditText isbn;
    private String book_name = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_book);
        isbn = (EditText) findViewById(R.id.sellbook_isbn);
    }
    public void identify(View v){
        Long to_send = Long.parseLong(isbn.getText().toString());
        RequestServer requestServer = new RequestServer();
        book_name = requestServer.identify_book(to_send);
        if(book_name != null){
            TextView textView = (TextView) findViewById(R.id.sellbook_identified);
            textView.setText(book_name);
        }
        else{
            Toast.makeText(this, "Incorrect isbn entered", Toast.LENGTH_LONG).show();
        }
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
}
