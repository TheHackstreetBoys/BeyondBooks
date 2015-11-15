package in.ac.iiitv.beyondbooks;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Wireframe7 extends Activity {

    ListView comments,sellers ;
    CustomAdapter_frame7comment adapter_comments,adapter_sellers;
    Intent intent;
    UserData userData;
    ImageView bookimage;
    RatingBar bookrating,rating;
    TextView bookdesc,general,student,faculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wireframe7);
        intent = getIntent();
        userData = (UserData) intent.getSerializableExtra("user_data");
        Long isbn = Long.parseLong(intent.getStringExtra("isbn"));
        RequestServer requestServer = new RequestServer();
        final BookDetails bookDetails = requestServer.book_page(isbn, userData.getId());

        //set image of the book
        bookimage = (ImageView) findViewById(R.id.book_image);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put(userData.getImage_link(), R.drawable.book_image);
        bookimage.setImageResource(map.get(userData.getImage_link()));

        //set ratings of the book
        bookrating = (RatingBar) findViewById(R.id.book_rating);
        bookrating.setRating(bookDetails.getPublic_ratings());

        //set description of the book
        bookdesc = (TextView) findViewById(R.id.book_description);
        bookdesc.setText(bookDetails.getAbout_book());

        //set ratings general student and faculty on click of textview
        general = (TextView)findViewById(R.id.general_rating);
        student = (TextView) findViewById(R.id.student_rating);
        faculty = (TextView) findViewById(R.id.faculty_rating);

        rating = (RatingBar) findViewById(R.id.rating);

        OnClickListener cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = v.getId();
                switch (id) {
                    case R.id.general_rating:
                        rating.setRating(bookDetails.getPublic_ratings());
                        break;
                    case R.id.student_rating:
                        rating.setRating(bookDetails.getStudent_ratings());
                        break;
                    case R.id.faculty_rating:
                        rating.setRating(bookDetails.getFaculty_ratings());
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

        
        //fill the comment list
        //TODO
        comments = (ListView) findViewById(R.id.comments);
        adapter_comments = new CustomAdapter_frame7comment(this,bookDetails.getComments());
        comments.setAdapter(adapter_comments);
        //sellers.setAdapter(new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1,android.R.id.text1,getcomments(bookDetails)));
        //bookdetail should also contain an arraylist of comments on this book


        //fill the sellers available listview
        //TODO
        sellers = (ListView) findViewById(R.id.sellers);
        sellers.setAdapter(new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1,android.R.id.text1,getsellers(bookDetails)));




    }

    private ArrayList<String> getsellers(BookDetails bookDetails) {
        ArrayList<String> sellers = new ArrayList<>(0);

        for(int i=0;i<bookDetails.getSellers().size();i++){
            sellers.add(bookDetails.getSellers().get(i).first.getUser_name()+"\n"+bookDetails.getSellers().get(i).second);
        }

        return sellers;
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
        intent.putExtra("user_data", userData);
        startActivity(intent);
    }
}
