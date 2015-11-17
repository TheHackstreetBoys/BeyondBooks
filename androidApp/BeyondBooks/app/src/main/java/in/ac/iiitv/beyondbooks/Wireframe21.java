package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
        //comment = editText_comment.getText().toString();
        giverating = (RatingBar) findViewById(R.id.giverating);
        giverating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,boolean fromUser) {
                bookrating = rating;
                Toast.makeText( getApplicationContext(),"rating value is "+rating , Toast.LENGTH_LONG).show();
                //txtRatingValue.setText(String.valueOf(rating));
                System.out.println("Rating value is\n"+bookrating );
            }
        });

    }
    public void submit_review(View v){
        Intent intent = getIntent();
        Long isbn = Long.parseLong(intent.getStringExtra("isbn"));
        UserData userData = MainActivity.userData;
        Integer user_id = userData.getId();
        comment = editText_comment.getText().toString();
        RequestServer requestServer = new RequestServer();
        Boolean result = requestServer.review_submit(user_id, isbn, bookrating, comment);
        if(result){
            Toast.makeText( getApplicationContext(),"Your reviews have been submitted successfully" , Toast.LENGTH_LONG).show();
            Intent new_intent = new Intent(this, Wireframe7.class);
            new_intent.putExtra("isbn", isbn.toString());
            new_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(new_intent);
        }
        else{
            Toast.makeText( getApplicationContext(),"Some error occured" , Toast.LENGTH_LONG).show();
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

        }

        return super.onOptionsItemSelected(item);
    }

}
