package in.ac.iiitv.beyondbooks;

import android.app.Activity;
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
    ArrayList<String> values_comments,values_sellers;
    ArrayAdapter<String> adapter_comments,adapter_sellers;
    Intent intent;
    UserData userData;
    ImageView bookimage;
    RatingBar bookrating;
    TextView bookdesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wireframe7);
        intent = getIntent();
        userData = (UserData) intent.getSerializableExtra("user_data");
        Long isbn = Long.parseLong(intent.getStringExtra("isbn"));
        RequestServer requestServer = new RequestServer();
        BookDetails bookDetails = requestServer.book_page(isbn, userData.getId());

        //set image of the book
        bookimage = (ImageView) findViewById(R.id.book_image);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put(userData.getImage_link(), R.drawable.book_image);
        bookimage.setImageResource(map.get(userData.getImage_link()));
        //TODO


        comments = (ListView) findViewById(R.id.comments);
        values_comments = new ArrayList<>(0);
        for(int i=0;i<10;i++){
            values_comments.add("comments"+i);
        }

        adapter_comments = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,values_comments);
        comments.setAdapter(adapter_comments);

        //for sellers listview
        sellers = (ListView) findViewById(R.id.sellers);
        values_sellers = new ArrayList<>(0);
        for(int i=0;i<10;i++){
            values_sellers.add("seller" + i);
        }

        adapter_sellers = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,values_sellers);
        sellers.setAdapter(adapter_sellers);
    }

    public void give_review(View v){
        intent = new Intent(this, Wireframe21.class);
        intent.putExtra("user_data", userData);
        startActivity(intent);
    }
}
