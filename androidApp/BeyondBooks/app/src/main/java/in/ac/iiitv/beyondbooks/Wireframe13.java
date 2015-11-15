package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Wireframe13 extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayList<ForumOverview> top_rated, recent_discussions;
    ListView toprated,recent;
    ArrayAdapter<String> adapter_toprated,adapter_recent;
    private Button newdis; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wireframe13);
        RequestServer requestServer = new RequestServer();
        top_rated = requestServer.top_rated_discussions();
        recent_discussions = requestServer.recent_discussions();
        
        //Button to start a new discussion
        newdis = (Button) findViewById(R.id.startdiscussion);
        newdis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent should be checked
                startActivity(new Intent(getApplicationContext(),Frame15.class));
            }
        });
        
        
        // Get ListView object from xml
        toprated = (ListView) findViewById(R.id.topratedlist);

        // Defined Array values to show in ListView
        ArrayList<String> values_toprated = new ArrayList<String>(0) ;
        for(int i=0;i<top_rated.size();i++){
            values_toprated.add(top_rated.get(i).getTitle()+"\n"+top_rated.get(i).getAuthor());
        }
        adapter_toprated = new ArrayAdapter<String>(this,R.layout.frame10_list_view, values_toprated);
        // Assign adapter to ListView
        toprated.setAdapter(adapter_toprated);


        //For recent discussion
        // Get ListView object from xml
        recent = (ListView) findViewById(R.id.recentlist);
        // Defined Array values to show in ListView
        ArrayList<String> values_recent = new ArrayList<String>(0);
        for (int i=0;i<recent_discussions.size();i++){
            values_recent.add(recent_discussions.get(i).getTitle()+"\n"+recent_discussions.get(i).getAuthor());
        }
        adapter_recent = new ArrayAdapter<String>(this,R.layout.frame10_list_view, values_recent);
        // Assign adapter to ListView
        recent.setAdapter(adapter_recent);

        toprated.setOnItemClickListener(this);
        recent.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        int rid = parent.getId();
        switch (rid)
        {
            case R.id.topratedlist:
                Toast.makeText(this,top_rated.get(position).getAuthor(),Toast.LENGTH_LONG).show();
                //intent should be checked
                startActivity(new Intent(getApplicationContext(), Frame14.class));
                break;
            case R.id.recentlist:
                Toast.makeText(this,recent_discussions.get(position).getAuthor(),Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), Frame14.class));
                break;
        }
    }
}
