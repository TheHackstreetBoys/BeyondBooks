package in.ac.iiitv.beyondbooks;

import android.app.ActionBar;
<<<<<<< HEAD
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
=======
import android.content.Intent;
>>>>>>> e2ae5cfdd6630901551f836e14eac2ae3a1e708a
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

<<<<<<< HEAD
    private HorizontalScrollView hsv_new,hsv_top;
    private LinearLayout topbook,newbook;
    private TextView tv1;
    private ArrayList<NewlyAdded> newlyAddedArrayList;
    private ArrayList<NewlyAdded> topRatedArrayList;
    private ImageView iv1;
    private RatingBar rb1;
=======
    HorizontalScrollView hsv_new,hsv_top;
    LinearLayout topbook,newbook;
    TextView tv1;
    ArrayList<NewlyAdded> newlyAddedArrayList;
    ArrayList<NewlyAdded> topRatedArrayList;
>>>>>>> e2ae5cfdd6630901551f836e14eac2ae3a1e708a
    private ViewPager newbooks,topratedbook;


    private PagerAdapter newbooksadapter;
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
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

        //set toprated book
        topratedbook = (ViewPager) findViewById(R.id.book_toprated_pager);
        topratedbook.setAdapter(new NewBooksPagerAdapter(getSupportFragmentManager()));

        //TODO add image, rating and book title

<<<<<<< HEAD
        //addbook button
        addbook = (Button) findViewById(R.id.addBook);
        addbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddBook.class));
            }
        });

=======
>>>>>>> e2ae5cfdd6630901551f836e14eac2ae3a1e708a

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

        //@Override
        public Object instantiateItem(ViewGroup container, Bitmap image,int position) {

            LinearLayout ly = (LinearLayout)findViewById(R.id.pagerview);
            LinearLayout ly1,ly2;
            ly1 = new LinearLayout(getApplicationContext());
            ly2 = new LinearLayout(getApplicationContext());

            TextView tv = new TextView(getApplicationContext());
            RatingBar rb = new RatingBar(getApplicationContext());

            //to set the image
            ImageView iv = new ImageView(BookViewPage.this);
            iv.setImageBitmap(image);
            ly1.addView(iv);

            tv.setText(naa.get(position).getBook_name());
            rb.setRating(naa.get(position).getRatings());
            ly2.addView(tv);
            ly2.addView(rb);

            ly.addView(ly1);
            ly.addView(ly2);





            ly.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),Wireframe7.class));
                }
            });

            container.addView(ly);
            return ly;
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
            case R.id.option_reviewed_books:
                in = new Intent(this,Frame11.class);
                startActivity(in);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
