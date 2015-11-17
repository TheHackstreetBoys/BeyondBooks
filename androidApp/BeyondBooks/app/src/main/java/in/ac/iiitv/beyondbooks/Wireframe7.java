package in.ac.iiitv.beyondbooks;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Wireframe7 extends Activity {

    ListView comments,sellers ;
    CustomAdapter_frame7comment adapter_comments,adapter_sellers;
    Intent intent;
    ImageView bookimage;
    RatingBar bookrating,rating;
    Long isbn;
    TextView bookdesc,general,student,faculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wireframe7);
        intent = getIntent();


        isbn = Long.parseLong(intent.getStringExtra("isbn"));
        final RequestServer requestServer = new RequestServer();
        final BookDetails bookDetails = requestServer.book_page(isbn, MainActivity.userData.getId());

        //set image of the book
        //TODO get image from server to set the image of the book
        bookimage = (ImageView) findViewById(R.id.book_image);
        Bitmap image_to_set = requestServer.getImage("books_pics/"+intent.getStringExtra("isbn")+".jpg");
        bookimage.setImageBitmap(image_to_set);
        System.out.println("Entering here...");
        //bookdetails should contain bookimage link
       // map.put(bookDetails.get, R.drawable.book_image);
       // bookimage.setImageResource(map.get(bookDetails.getImagelink()));

        //set ratings of the book
        bookrating = (RatingBar) findViewById(R.id.book_rating);
        bookrating.setRating(bookDetails.getPublic_ratings());
        System.out.println("Entering here...1");
        //set description of the book
        bookdesc = (TextView) findViewById(R.id.book_description);
        bookdesc.setText(bookDetails.getAbout_book());
        System.out.println("Entering here2...");
        //set ratings general student and faculty on click of textview
        general = (TextView)findViewById(R.id.general_rating);
        student = (TextView) findViewById(R.id.student_rating);
        faculty = (TextView) findViewById(R.id.faculty_rating);
        System.out.println("Entering here3...");
        rating = (RatingBar) findViewById(R.id.rating);
        System.out.println("Entering here4...");
        OnClickListener cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = v.getId();
                switch (id) {
                    case R.id.general_rating:
                        rating.setRating(bookDetails.getPublic_ratings());
                        Toast.makeText(getApplicationContext(),"general rating",Toast.LENGTH_LONG ).show();
                        break;
                    case R.id.student_rating:
                        rating.setRating(bookDetails.getStudent_ratings());
                        Toast.makeText(getApplicationContext(),"student rating",Toast.LENGTH_LONG ).show();
                        break;
                    case R.id.faculty_rating:
                        rating.setRating(bookDetails.getFaculty_ratings());
                        Toast.makeText(getApplicationContext(),"faculty rating",Toast.LENGTH_LONG ).show();
                        break;
                    default:
                        System.out.print("Some error : not able to set the rating of different kind(general,student,faculty)");
                        break;

                }
            }
        };
        general.setOnClickListener(cl);
        student.setOnClickListener(cl);
        faculty.setOnClickListener(cl);
        System.out.println("Entering here5...");
        
        //fill the comment list
        //TODO
        comments = (ListView) findViewById(R.id.comments);
        ArrayList<Pair<String, String>> comments_temp = bookDetails.getComments();
        System.out.println("Entering here6...");
        if (comments_temp == null || comments_temp.size() == 0)
            comments_temp.add(new Pair<String, String>("nothing to show", "nothing to show"));
        System.out.println("Entering here7...");
        System.out.println("Entering here9...");
        comments.setAdapter(new MyNewAdapter(this, comments_temp));
                System.out.println("Entering here10...");
        //sellers.setAdapter(new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1,android.R.id.text1,getcomments(bookDetails)));
        //bookdetail should also contain an arraylist of comments on this book


        //fill the sellers available listview
        //TODO
        sellers = (ListView) findViewById(R.id.sellers);
        System.out.println("Entering here11...");
        sellers.setAdapter(new MyAdapter(this, bookDetails.getSellers()));
        System.out.println("Entering here12...");
        final Intent seller_intent = new Intent(this, Wireframe20.class);
        System.out.println("Entering here13...");
        sellers.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer seller_pos = position;
                seller_intent.putExtra("price", bookDetails.getSellers().get(seller_pos).second.toString());
                seller_intent.putExtra("description", bookDetails.getAbout_book());
                seller_intent.putExtra("seller_description", requestServer.get_seller_description(isbn, bookDetails.getSellers().get(seller_pos).first.getId()));
                seller_intent.putExtra("age", requestServer.get_age(isbn, bookDetails.getSellers().get(seller_pos).first.getId()).toString());
                seller_intent.putExtra("isbn", isbn.toString());
                seller_intent.putExtra("seller_id", bookDetails.getSellers().get(seller_pos).first.getId().toString());
                System.out.println("Reached1");
                startActivity(seller_intent);
            }
        });



    }


    /*
    private ArrayList<String> getcomments(BookDetails bookDetails) {
        ArrayList<String> sellers = new ArrayList<>(0);

        for(int i=0;i<bookDetails.getComments().size();i++){
            sellers.add(bookDetails.getComments().get(i).second+"\n"+bookDetails.getComments().get(i).first);
        }

        return sellers;
    }*/

    public void give_review(View v){
        intent = new Intent(this, Wireframe21.class);

        intent.putExtra("isbn", isbn.toString());

        startActivity(intent);
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

                in = new Intent(this, MainActivity.class);
                startActivity(in);
                break;
            case R.id.option_user_profile:
                in = new Intent(this,Wireframe8.class);
                startActivity(in);
                break;
            case R.id.option_activity_on_forum:
                in = new Intent(this,Wireframe8.class);
                startActivity(in);
                break;
            case R.id.option_book_shelf:
                in = new Intent(this,Wireframe8.class);
                startActivity(in);
                break;
            case R.id.option_forum:
                in = new Intent(this,Wireframe8.class);
                startActivity(in);
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
