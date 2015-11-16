package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class Wireframe21 extends AppCompatActivity {

    RatingBar giverating;
    //send this book rating to the sever
    float bookrating;
    EditText editText_comment;
    String comment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wireframe21);
        editText_comment = (EditText)findViewById(R.id.comment);
        comment = editText_comment.getText().toString();
        giverating = (RatingBar) findViewById(R.id.giverating);
        giverating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,boolean fromUser) {
                bookrating = rating;
                Toast.makeText( getApplicationContext(),"rating value is "+rating , Toast.LENGTH_LONG).show();
                //txtRatingValue.setText(String.valueOf(rating));
                System.out.println("Rating value is\n"+bookrating );
            }
        });
        Intent intent = getIntent();
        Long isbn = Long.parseLong(intent.getStringExtra("isbn"));
        UserData userData = (UserData)intent.getSerializableExtra("user_data");
        Integer user_id = userData.getId();
        RequestServer requestServer = new RequestServer();
        Boolean result = requestServer.review_submit(user_id, isbn, bookrating, comment);
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

}
