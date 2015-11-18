package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Frame14 extends AppCompatActivity implements View.OnClickListener {
    private Intent intent;
    private TextView topic,description;
    private ListView comments;
    private EditText yc;
    private Button submit;
    private String usercomment;
    private Integer q_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame14);
        intent = getIntent();

        q_id = Integer.parseInt(intent.getStringExtra("q_id"));
        System.out.println("frame14 is : " + q_id);

        ForumDetails forumDetails = (new RequestServer()).forumDetails(q_id);



        //set topic of the discussion
        topic = (TextView) findViewById(R.id.forum_topic);
        topic.setText(forumDetails.getTitle());

        //set descrition of the description
        description = (TextView) findViewById(R.id.forumdesc);
        description.setText(forumDetails.getDetails());

        //populate the listview
        comments = (ListView) findViewById(R.id.comment_list);
        comments.setAdapter(new ArrayAdapter<String>(this,R.layout.frame10_list_view,getcomentlist(forumDetails)));

        //get user comment
        yc = (EditText) findViewById(R.id.frame14_comment);

        //implement button - send user comment on clicking the button
        submit = (Button) findViewById(R.id.frame14_submit);
        submit.setOnClickListener(this);

    }

    private ArrayList<String> getcomentlist(ForumDetails forumDetails) {
        ArrayList<String> comments = new ArrayList<>();
        for(int i=0;i<forumDetails.getComments().size();i++){
            comments.add(i,forumDetails.getComments().get(i).getText());
        }
     return comments;
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



    @Override
    public void onClick(View v) {
        usercomment = yc.getText().toString();
        System.out.println(usercomment);
        new RequestServer().send_comment(MainActivity.userData.getId(), q_id, usercomment);
        Intent in = new Intent(this,Frame14.class);
        in.putExtra("q_id",q_id.toString());
        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
    }
}
