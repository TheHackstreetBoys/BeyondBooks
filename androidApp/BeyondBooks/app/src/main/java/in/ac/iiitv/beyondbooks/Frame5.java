package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Frame5 extends AppCompatActivity {
    SearchOutputReturn searchOutputReturn;
    ListView review_list,forum_list;
    ArrayAdapter<String> adapter_review,adapter_forum;
    ArrayList<String> reviewarr,forumarr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame5);
        Intent intent = getIntent();

        String qs = getIntent().getStringExtra("qs");

        searchOutputReturn = (new RequestServer()).search(qs);

        review_list = (ListView) findViewById(R.id.frame5_review);

        forum_list = (ListView) findViewById(R.id.frame5_forum);

        reviewarr = setreview_listitem(searchOutputReturn);
        forumarr = setforum_listitem(searchOutputReturn);

        adapter_review = new ArrayAdapter<String>(this, R.layout.frame10_list_view,reviewarr);

        adapter_forum = new ArrayAdapter<String>(this, R.layout.frame10_list_view,forumarr);

        review_list.setAdapter(adapter_review);
        forum_list.setAdapter(adapter_forum);

    }



    public ArrayList<String> setreview_listitem( SearchOutputReturn sr){
        ArrayList<String> values = new ArrayList<String>(0);

        for(int reviews=0;reviews<sr.getReview().size();reviews++){
            values.add(sr.getReview().get(reviews).getBook_name());
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

}


