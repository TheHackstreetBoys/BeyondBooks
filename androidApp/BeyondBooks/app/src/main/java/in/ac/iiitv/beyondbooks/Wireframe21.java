package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
}
