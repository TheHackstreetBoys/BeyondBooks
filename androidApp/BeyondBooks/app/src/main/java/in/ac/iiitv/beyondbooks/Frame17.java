package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    private TextView tags;
    private String question;
    private String details;
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


        System.out.println("user id from main activity : "+MainActivity.userData.getId());
        question = getIntent().getStringExtra("question");
        details = getIntent().getStringExtra("details");

        System.out.println(question);
        System.out.println(details);



        facultycopy = new ArrayList<String>(faculty);
        tags = (TextView)findViewById(R.id.frame17_tag_topics);
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
                ForumDetails fd = new ForumDetails(question,MainActivity.userData.getUser_name(),null,MainActivity.userData.getId(),null);
                fd.setDetails(details);
                fd.setFaculty_tags(facet.getText().toString());
                fd.setTags(tags.getText().toString());
//                System.out.println(MainActivity.userData.getUser_name());
                System.out.println(MainActivity.userData.getId());
                new RequestServer().add_forum_question(fd);

                Intent in = new Intent(this,Wireframe13.class);
                in.putExtra("user_data",MainActivity.userData);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
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
            facet.setText(facet.getText()+","+facultycopy.get(position));
        }
        facultycopy.remove(position);
        facultyadapter.notifyDataSetChanged();
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
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
            case R.id.option_sell:
                in = new Intent(this,SellBook.class);
                startActivity(in);
                break;
            case R.id.option_sell_list:
                in = new Intent(this,SellingList.class);
                startActivity(in);
                break;
            case R.id.option_logout:
                in = new Intent(this,MainActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
