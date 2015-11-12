package in.ac.iiitv.beyondbooks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Frame6 extends AppCompatActivity implements View.OnClickListener {

    Button giveReviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame6);
        // TODO get book details method
        giveReviews = (Button)findViewById(R.id.frame6_give_reviews);
        giveReviews.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //TODO create intent to connect to next wireframe
    }
}
