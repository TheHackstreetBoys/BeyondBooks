package in.ac.iiitv.beyondbooks;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class wireframe7 extends Activity {

    ListView comments,sellers ;
    ArrayList<String> values_comments,values_sellers;
    ArrayAdapter<String> adapter_comments,adapter_sellers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wireframe7);

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
}
