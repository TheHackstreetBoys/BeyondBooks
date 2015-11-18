package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
                startActivity(new Intent(getApplicationContext(), BookViewPage.class));
            }
        });
    }

    public void add_book(View v) {
        RequestServer requestServer = new RequestServer();
        System.out.println("adfa "+addBook_isbn);
        Long isbn = Long.parseLong(addBook_isbn.getText().toString());
        Intent intent = getIntent();
        Boolean result = requestServer.add_book(isbn, MainActivity.userData.getId());
        if(result){
            Toast.makeText(this, "Book successfully added to the database", Toast.LENGTH_LONG).show();
            intent = new Intent(this, BookViewPage.class);
            intent.putExtra("user_data", MainActivity.userData);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "The isbn number is invalid or the book is already in the database", Toast.LENGTH_LONG).show();
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
