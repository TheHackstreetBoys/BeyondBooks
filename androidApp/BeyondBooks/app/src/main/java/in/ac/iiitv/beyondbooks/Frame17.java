package in.ac.iiitv.beyondbooks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Frame17 extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    ArrayList<String> faculty;
    private Button submit,clear;
    private ArrayAdapter<String> facultyadapter;
    private ListView faclist;
    private ArrayList<String> facultycopy;
    private TextView facet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame17);
        submit = (Button) findViewById(R.id.frame17_submit);
        submit.setOnClickListener(this);
        clear =(Button) findViewById(R.id.frame17_clear);
        clear.setOnClickListener(this);
        RequestServer requestServer = new RequestServer();
        faculty = requestServer.get_faculty();
        int i;

        facultycopy = new ArrayList<String>(faculty);
        facet = (TextView) findViewById(R.id.frame17_fac);
        facultyadapter = new ArrayAdapter<String>(this,R.layout.frame10_list_view,facultycopy);
        faclist = (ListView) findViewById(R.id.frame17_faclist);
        faclist.setAdapter(facultyadapter);
        faclist.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.frame17_clear:
                facultycopy = new ArrayList<String>(faculty);
                facultyadapter = new ArrayAdapter<String>(this,R.layout.frame10_list_view,facultycopy);
                faclist.setAdapter(facultyadapter);
                facet.setText("");
                break;
            case R.id.frame17_submit:
                //TODO  Do it tyagi
                // send the forum detail to the server and also set intent to the next activity

                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(facet.getText().toString().compareTo("")==0)
        {
            facet.setText(facultycopy.get(position));
        }
        else
        {
            facet.setText(facet.getText()+", "+facultycopy.get(position));
        }
        facultycopy.remove(position);
        facultyadapter.notifyDataSetChanged();
    }
}
