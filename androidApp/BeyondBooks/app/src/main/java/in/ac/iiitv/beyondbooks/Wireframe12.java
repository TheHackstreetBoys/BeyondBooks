package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Wireframe12 extends AppCompatActivity {

    ListView started_list, commented_list;
    ArrayAdapter<String> adapter_started, adapter_commented;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wireframe12);

        //Added by anjul
        Intent intent = getIntent();
        UserData userData = (UserData) intent.getSerializableExtra("user_data");
        RequestServer requestServer = new RequestServer();

        ForumActivities forumActivities = requestServer.get_forum_activities(/*userData.getId()*/201351010);
        // Till here

        started_list = (ListView) findViewById(R.id.harkat_startedlist);
        commented_list = (ListView) findViewById(R.id.harkat_commentedlist);
        ArrayList<String> values = new ArrayList<String>();

        for (int i = 0; i < forumActivities.getQuestions_started().size(); i++) {
            values.add(forumActivities.getQuestions_started().get(i).getTitle());
        }
        adapter_started = new ArrayAdapter<String>(this, R.layout.frame10_list_view, values);

        values = new ArrayList<String>();
        for (int i = 0; i < forumActivities.getQuestions_started().size(); i++) {
            values.add(forumActivities.getCommented().get(i).getQ_title());
        }
        adapter_commented = new ArrayAdapter<String>(this, R.layout.frame10_list_view, values);

        started_list.setAdapter(adapter_started);
        commented_list.setAdapter(adapter_commented);
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