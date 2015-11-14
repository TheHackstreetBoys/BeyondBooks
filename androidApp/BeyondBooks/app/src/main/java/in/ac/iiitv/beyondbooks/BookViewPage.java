package in.ac.iiitv.beyondbooks;

import android.app.ActionBar;
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
    TextView tv1;
    ImageView iv1;
    RatingBar rb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_view_page);

        //set image
        iv1 = (ImageView) findViewById(R.id.image_newbook);
        iv1.setImageDrawable(getResources().getDrawable(R.drawable.user_image));

        tv1 = (TextView) findViewById(R.id.newbookname);
        tv1.setText("Disrete math");

        rb1 = (RatingBar) findViewById(R.id.ratingsnew);
        rb1.setRating(3);


        topbook = (LinearLayout) findViewById(R.id.linear_topbook);

        LinearLayout layout_temp = new LinearLayout(this);
        layout_temp.addView(iv1);
        layout_temp.addView(tv1);
        layout_temp.addView(rb1);

        topbook.addView(layout_temp);

        hsv.addView(topbook);
        /*
        hsv1 = (HorizontalScrollView)findViewById(R.id.toprated);
        ImageView iv2 = new ImageView(this);
        iv1.setImageDrawable(getDrawable(R.mipmap.ic_launcher));
        hsv1.addView(iv2);
        */


    }


}
