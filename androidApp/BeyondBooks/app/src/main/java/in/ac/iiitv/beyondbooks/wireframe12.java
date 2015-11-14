package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class wireframe12 extends AppCompatActivity {

    ListView started_list,commented_list;
    ArrayAdapter<String> adapter_started,adapter_commented;
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

        adapter_started = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,setstarted_listitem(forumActivities));
        adapter_commented = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,setstarted_listitem(forumActivities));

        started_list.setAdapter(adapter_started);
        commented_list.setAdapter(adapter_commented);
    }


    public ArrayList<String> setstarted_listitem( ForumActivities fa){
        ArrayList<String> values = new ArrayList<String>(0);

        for(int i=0;i<fa.getQuestions_started().size();i++){
            values.add(fa.getQuestions_started().get(i).getTitle());
        }
        return values;
    }

    public ArrayList<String> setcommented_listitem( ForumActivities fa){
        ArrayList<String> values = new ArrayList<String>(0);

        for(int i=0;i<fa.getCommented().size();i++){
            values.add(fa.getCommented().get(i).getQ_title());
        }
        return values;
    }
}
