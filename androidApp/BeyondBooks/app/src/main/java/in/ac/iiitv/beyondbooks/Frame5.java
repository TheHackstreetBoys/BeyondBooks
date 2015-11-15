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

        adapter_review = new ArrayAdapter<String>(this, R.layout.activity_frame5,setreview_listitem(searchOutputReturn));
        adapter_buysell = new ArrayAdapter<String>(this, R.layout.activity_frame5,setbuysell_listitem(searchOutputReturn));
        adapter_forum = new ArrayAdapter<String>(this, R.layout.activity_frame5,setforum_listitem(searchOutputReturn));

        review_list.setAdapter(adapter_review);
        buysell_list.setAdapter(adapter_buysell);
        forum_list.setAdapter(adapter_forum);

    }



    public ArrayList<String> setreview_listitem( SearchOutputReturn sr){
        ArrayList<String> values = new ArrayList<String>(0);

        for(int reviews=0;reviews<sr.getReview().size();reviews++){
            values.add(sr.getReview().get(reviews).getBook_name());
        }
        return values;
    }



    public ArrayList<String> setbuysell_listitem(SearchOutputReturn sr){
        ArrayList<String> values = new ArrayList<String>(0);

        for(int i=0;i<sr.getBuy_sell().size();i++){
            values.add(sr.getBuy_sell().get(i).getBook_name());
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


