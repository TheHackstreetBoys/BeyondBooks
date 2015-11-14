package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class wireframe8 extends AppCompatActivity {
    private ArrayList<String> notifications;
    ImageView user_image;
    ListView notification_list ;
    TextView username,userid;
    ArrayAdapter<String> adapter_notification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wireframe8);

        //Added by Anjul Tyagi
        notifications = new ArrayList<String>();
        RequestServer requestServer = new RequestServer();
        Intent intent = getIntent();
        UserData userData = (UserData)intent.getSerializableExtra("user_data");
        userData.setForumActivities(requestServer.get_forum_activities(userData.getId()));
        UserData temp = requestServer.get_user_name_image(userData.getId());
        userData.setImage_link(temp.getImage_link());
        userData.setUser_name(temp.getUser_name());
        UserData temp2 = requestServer.get_activities(userData.getId());
        userData.setReviewed(temp2.getReviewed());
        userData.setUploads(temp2.getUploads());
        notifications = requestServer.get_notification(userData.getId());
        //Till here... userData contains every information about the user. Use it to populate the page.

        //set image of the user
        user_image = (ImageView) findViewById(R.id.user_image);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put(userData.getImage_link(), R.drawable.user_image);
        user_image.setImageResource(map.get(userData.getImage_link()));

        //set username
        username = (TextView) findViewById(R.id.user_name);
        username.setText(userData.getUser_name());
        //set user id
        userid = (TextView) findViewById(R.id.user_id);
        userid.setText(userData.getId());


        notification_list = (ListView) findViewById(R.id.list_notification);
        notifications = new ArrayList<>(0);
        for(int i=0;i<10;i++){
            notifications.add("notification"+i);
        }

        adapter_notification = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,notifications);
        notification_list.setAdapter(adapter_notification);
    }
}
