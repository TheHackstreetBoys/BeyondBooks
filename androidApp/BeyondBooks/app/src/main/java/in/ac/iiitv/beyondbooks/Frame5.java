package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Frame5 extends AppCompatActivity {
    SearchOutputReturn searchOutputReturn;
    ListView review_list,buysell_list,forum_list;
    ArrayAdapter<String> adapter_review,adapter_buysell,adapter_forum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame5);
        Intent intent = getIntent();
        searchOutputReturn = (SearchOutputReturn) intent.getSerializableExtra("search_results");

        review_list = (ListView) findViewById(R.id.review);
        buysell_list = (ListView) findViewById(R.id.buy_sell);
        forum_list = (ListView) findViewById(R.id.forum);

        adapter_review = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,setreview_listitem(searchOutputReturn));
        adapter_buysell = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,setbuysell_listitem(searchOutputReturn));
        adapter_forum = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,setforum_listitem(searchOutputReturn));

        review_list.setAdapter(adapter_review);
        buysell_list.setAdapter(adapter_buysell);
        forum_list.setAdapter(adapter_forum);

    }



    public ArrayList<String> setreview_listitem( SearchOutputReturn sr){
        ArrayList<String> values = new ArrayList<String>(0);
        ArrayList<NewlyAdded> values_review = sr.getReview();
        for(int reviews=0;reviews<values_review.size();reviews++){
            values.add(values_review.get(reviews).getBook_name());
        }
        return values;
    }



    public ArrayList<String> setbuysell_listitem(SearchOutputReturn sr){
        ArrayList<String> values = new ArrayList<String>(0);
        ArrayList<NewlyAdded> values_buysell = sr.getBuy_sell();
        for(int i=0;i<values_buysell.size();i++){
            values.add(values_buysell.get(i).getBook_name());
        }
        return values;
    }


    public ArrayList<String> setforum_listitem(SearchOutputReturn sr){
        ArrayList<String> values = new ArrayList<String>(0);
        ArrayList<ForumOverview> values_forum = sr.getForum();
        for(int i=0;i<values_forum.size();i++){
            values.add(values_forum.get(i).getTitle());
        }
        return values;
    }

}


