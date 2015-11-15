package in.ac.iiitv.beyondbooks;

import android.app.ActionBar;
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
import android.view.View;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BookViewPage extends FragmentActivity implements NewBook.OnFragmentInteractionListener {

    HorizontalScrollView hsv_new,hsv_top;
    LinearLayout topbook,newbook;
    TextView tv1;
    ArrayList<NewlyAdded> newlyAddedArrayList;
    ArrayList<NewlyAdded> topRatedArrayList;
    ImageView iv1;
    RatingBar rb1;
    private ViewPager newbooks;
    private PagerAdapter newbooksadapter;

    private int numOfSlides;
    ArrayList<NewlyAdded> naa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_view_page);
        RequestServer rs = new RequestServer();
        naa = rs.newly_added();

        numOfSlides = naa.size();
        newbooks = (ViewPager)findViewById(R.id.book_view_newadded_pager);
        newbooksadapter = new NewBooksPagerAdapter(getSupportFragmentManager(),naa);
        newbooks.setAdapter(newbooksadapter);



    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private class NewBooksPagerAdapter extends FragmentStatePagerAdapter
    {
        ArrayList<NewlyAdded> nav;
        public NewBooksPagerAdapter(FragmentManager fm, ArrayList<NewlyAdded> naa)
        {
            super(fm);
            nav = naa;
        }
        @Override
        public Fragment getItem(int position)
        {
            return new NewBook(naa.get(position));
        }

        @Override
        public int getCount() {
            return numOfSlides;
        }
    }

}
