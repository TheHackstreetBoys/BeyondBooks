package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Frame14 extends AppCompatActivity {
    private Intent intent;
    private TextView topic,description;
    private ListView comments;
    private EditText yourcomment;
    private Button submit;
    private String usercomment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame14);
        intent = getIntent();

        final Integer q_id = Integer.parseInt(intent.getStringExtra("q_id"));
        System.out.println("frame14 is : "+q_id);
        final UserData userData = (UserData)intent.getSerializableExtra("user_data");
        final RequestServer requestServer = new RequestServer();
        ForumDetails forumDetails = requestServer.forumDetails(q_id);



        //set topic of the discussion
        topic = (TextView) findViewById(R.id.forum_topic);
        topic.setText(forumDetails.getTitle());

        //set descrition of the description
        description = (TextView) findViewById(R.id.forumdesc);
        description.setText(forumDetails.getDetails());

        //populate the listview
        comments = (ListView) findViewById(R.id.comment_list);
        comments.setAdapter(new ArrayAdapter<String>(this,android.R.layout.
                simple_list_item_1,android.R.id.text1,getcomentlist(forumDetails)));

        //get user comment
        yourcomment = (EditText) findViewById(R.id.yourcomment);
        usercomment = String.valueOf(yourcomment.getText());

        //implement button - send user comment on clicking the button
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestServer.send_comment(q_id,userData.getId(),usercomment);
            }
        });

    }

    private ArrayList<String> getcomentlist(ForumDetails forumDetails) {
        ArrayList<String> comments = new ArrayList<>();
        for(int i=0;i<forumDetails.getComments().size();i++){
            comments.add(i,forumDetails.getComments().get(i).getText());
        }
     return comments;
    }


}
