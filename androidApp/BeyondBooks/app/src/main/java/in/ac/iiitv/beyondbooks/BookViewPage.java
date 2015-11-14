package in.ac.iiitv.beyondbooks;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;

public class BookViewPage extends AppCompatActivity {

    HorizontalScrollView hsv,hsv1;
    ArrayList<LinearLayout> topratedbooks,newaddedbooks;
    ArrayAdapter<LinearLayout> toprated,newadded;
    LinearLayout topbook,newbook;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_view_page);

        ImageView iv1 = new ImageView(this);
        iv1.setImageDrawable(getDrawable(R.mipmap.ic_launcher));
        TextView tv1 = new TextView(this);
        tv1.setText("Disrete math");
        RatingBar rb1 = new RatingBar(this);
        rb1.setRating(3);

        topbook = (LinearLayout) findViewById(R.id.linear_topbook);
        //topbook.setLayoutParams(new layout);
        topbook.addView(iv1);

        hsv.addView(iv1);

        hsv1 = (HorizontalScrollView)findViewById(R.id.toprated);
        ImageView iv2 = new ImageView(this);
        iv1.setImageDrawable(getDrawable(R.mipmap.ic_launcher));
        hsv1.addView(iv2);


    }


}
