package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class AddBook extends AppCompatActivity{

    Button addBook;
    EditText addBook_isbn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        addBook = (Button) findViewById(R.id.addBook);
        addBook_isbn = (EditText) findViewById(R.id.addBook_isbn);
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_book(v);
            }
        });
    }

    public void add_book(View v) {
        RequestServer requestServer = new RequestServer();
        Long isbn = Long.parseLong(addBook_isbn.getText().toString());
        Intent intent = getIntent();
        UserData userData = (UserData) intent.getSerializableExtra("user_data");
        Boolean result = requestServer.add_book(isbn, userData.getId());
        if(result){
            Toast.makeText(this, "Book successfully added to the database", Toast.LENGTH_LONG).show();
            intent = new Intent(this, BookViewPage.class);
            intent.putExtra("user_data", userData);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "The isbn number is invalid or the book is already in the database", Toast.LENGTH_LONG).show();
        }
    }
}
