package in.ac.iiitv.beyondbooks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class AddBook extends AppCompatActivity implements View.OnClickListener {

    Button addBook;
    EditText addBook_isbn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        addBook = (Button) findViewById(R.id.addBook);
        addBook_isbn = (EditText) findViewById(R.id.addBook_isbn);

        addBook.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String text = addBook_isbn.getText().toString();
        if(true)
        {
            Toast.makeText(AddBook.this, "This book already exists!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(AddBook.this,"Thank you for adding this book!", Toast.LENGTH_SHORT).show();
        }
    }
}
