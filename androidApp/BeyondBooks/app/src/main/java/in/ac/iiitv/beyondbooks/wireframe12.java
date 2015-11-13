package in.ac.iiitv.beyondbooks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class wireframe12 extends AppCompatActivity {

    ListView harkat_forum;
    ArrayList<String> harkats;
    ArrayAdapter<String> adapter_harkat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wireframe12);

        harkat_forum = (ListView) findViewById(R.id.harkatzlist);
        harkats = new ArrayList<>(0);
        for(int i=0;i<10;i++){
            harkats.add("harkats" + i);
        }

        adapter_harkat = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,harkats);
        harkat_forum.setAdapter(adapter_harkat);
    }
}
