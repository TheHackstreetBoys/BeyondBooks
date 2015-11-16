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

public class BookViewPage extends FragmentActivity implements NewBook.OnFragmentInteractionListener, View.OnClickListener {


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
    ArrayList<NewlyAdded> naa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_view_page);
        RequestServer rs = new RequestServer();
        naa = rs.newly_added();

        // set newly added book
        numOfSlides = naa.size();

        newbooks = (ViewPager)findViewById(R.id.book_view_newadded_pager);
        newbooksadapter = new NewBooksPagerAdapter(getSupportFragmentManager(),naa);

        newbooks.setAdapter(newbooksadapter);
        addbook = (Button) findViewById(R.id.book_view_btn);
        addbook.setOnClickListener(this);

        //top rated books


        topratedbook = (ViewPager)findViewById(R.id.book_toprated_pager);
        topratedadapter = new NewBooksPagerAdapter(getSupportFragmentManager(),naa);

        topratedbook.setAdapter(topratedadapter);
        addbook = (Button) findViewById(R.id.book_view_btn);
        addbook.setOnClickListener(this);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

        //set toprated book
        topratedbook = (ViewPager) findViewById(R.id.book_toprated_pager);
        topratedbook.setAdapter(new NewBooksPagerAdapter(getSupportFragmentManager()));

        //TODO add image, rating and book title


        //addbook button
        addbook = (Button) findViewById(R.id.addBook);
        addbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddBook.class));
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    private class NewBooksPagerAdapter extends FragmentStatePagerAdapter
    {

       public NewBooksPagerAdapter(FragmentManager fm){
           super(fm);
       }

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
        }

        return super.onOptionsItemSelected(item);
    }

}
