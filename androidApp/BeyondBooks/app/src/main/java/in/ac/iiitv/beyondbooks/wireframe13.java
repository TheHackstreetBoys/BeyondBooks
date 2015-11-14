package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class wireframe13 extends AppCompatActivity {

    ListView toprated,recent;
    ArrayAdapter<String> adapter_toprated,adapter_recent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wireframe13);

        // Get ListView object from xml
        toprated = (ListView) findViewById(R.id.topratedlist);

        // Defined Array values to show in ListView
        ArrayList<String> values_toprated = new ArrayList<String>(0) ;
        for(int i=0;i<10;i++){
            values_toprated.add("item"+i);
        }
        adapter_toprated = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, values_toprated);

        // Assign adapter to ListView
        toprated.setAdapter(adapter_toprated);


        //For recent discussion
        // Get ListView object from xml
        recent = (ListView) findViewById(R.id.recentlist);

        // Defined Array values to show in ListView
        ArrayList<String> values_recent = new ArrayList<String>(0);
        for (int i=0;i<10;i++){
            values_recent.add("string"+i);
        }
        adapter_recent = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, values_recent);

        // Assign adapter to ListView
        recent.setAdapter(adapter_recent);

    }





}
