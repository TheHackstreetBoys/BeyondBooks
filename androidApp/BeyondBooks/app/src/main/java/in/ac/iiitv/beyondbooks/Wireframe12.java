package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        ForumActivities forumActivities = requestServer.get_forum_activities(userData.getId());
        // Till here

        started_list = (ListView) findViewById(R.id.harkat_startedlist);
        commented_list = (ListView) findViewById(R.id.harkat_commentedlist);
        ArrayList<String> values = null;
        for (int i = 0; i < forumActivities.getQuestions_started().size(); i++) {
            values.add(forumActivities.getQuestions_started().get(i).getTitle());
        }
        adapter_started = new ArrayAdapter<String>(this, R.layout.frame10_list_view, values);
        for (int i = 0; i < forumActivities.getQuestions_started().size(); i++) {
            values.add(forumActivities.getCommented().get(i).getQ_title());
        }
        adapter_commented = new ArrayAdapter<String>(this, R.layout.frame10_list_view, values);

        started_list.setAdapter(adapter_started);
        commented_list.setAdapter(adapter_commented);
    }

}