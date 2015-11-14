package in.ac.iiitv.beyondbooks;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

public class BookViewPage extends AppCompatActivity {

    HorizontalScrollView hsv,hsv1;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_view_page);
        hsv = (HorizontalScrollView)findViewById(R.id.newadded);
        ImageView iv = new ImageView(this);
        iv.setImageDrawable(getDrawable(R.mipmap.book));
        hsv.addView(iv);

        hsv1 = (HorizontalScrollView)findViewById(R.id.toprated);
        ImageView iv1 = new ImageView(this);
        iv1.setImageDrawable(getDrawable(R.mipmap.ic_launcher));
        hsv.addView(iv1);


    }


}
