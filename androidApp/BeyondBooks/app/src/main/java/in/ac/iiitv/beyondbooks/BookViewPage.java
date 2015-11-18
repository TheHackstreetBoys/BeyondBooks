package in.ac.iiitv.beyondbooks;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BookViewPage extends AppCompatActivity implements NewBook.OnFragmentInteractionListener, View.OnClickListener {


    private HorizontalScrollView hsv_new,hsv_top;
    private LinearLayout topbook,newbook;
    private TextView tv1;
    private ArrayList<NewlyAdded> newlyAddedArrayList;
    private ArrayList<NewlyAdded> topRatedArrayList;
    private ImageView iv1;
    private RatingBar rb1;
    private ViewPager newbooks,topratedbook;
    private PagerAdapter newbooksadapter,topratedadapter;
    private Button addbook;

    private int numOfSlides;
    ArrayList<NewlyAdded> naa,top;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_view_page);
        RequestServer rs = new RequestServer();
        naa = rs.newly_added();
        top = rs.top_rated();

        // set newly added book
        numOfSlides = naa.size();

        newbooks = (ViewPager)findViewById(R.id.book_view_newadded_pager);
        newbooksadapter = new NewBooksPagerAdapter(getSupportFragmentManager(),naa,naa.size());
        newbooks.setAdapter(newbooksadapter);


        addbook = (Button) findViewById(R.id.book_view_btn);
        addbook.setOnClickListener(this);

        //top rated books


        numOfSlides = top.size();
        topratedbook = (ViewPager)findViewById(R.id.book_toprated_pager);
        topratedadapter = new NewBooksPagerAdapter(getSupportFragmentManager(),top,top.size());
        topratedbook.setAdapter(topratedadapter);

        newbooks.setPageTransformer(true, new ZoomOutPageTransformer());
        topratedbook.setPageTransformer(true,new ZoomOutPageTransformer());


    }


    @Override
    public void onFragmentInteraction(Long isbn) {
        Intent in = new Intent(this,Wireframe7.class);
        in.putExtra("isbn",isbn.toString());
        startActivity(in);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(),AddBook.class));
    }

    private class NewBooksPagerAdapter extends FragmentStatePagerAdapter
    {

       public NewBooksPagerAdapter(FragmentManager fm){
           super(fm);
       }

        ArrayList<NewlyAdded> nav;
        int size;
        public NewBooksPagerAdapter(FragmentManager fm, ArrayList<NewlyAdded> nax, int siz)

        {
            super(fm);
            size= siz;
            nav = nax;
        }

        @Override
        public Fragment getItem(int position)
        {
            return new NewBook(nav.get(position));
        }

        @Override
        public int getCount() {
            return size;
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

    private class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

}
