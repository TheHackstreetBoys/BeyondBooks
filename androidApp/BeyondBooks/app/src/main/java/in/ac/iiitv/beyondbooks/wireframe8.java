package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class wireframe8 extends AppCompatActivity {

    ListView notification_list ;
    ArrayAdapter<String> adapter_notification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wireframe8);

        //Added by Anjul Tyagi
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
        //Till here... userData contains every information about the user. Use it to populate the page.



        notification_list = (ListView) findViewById(R.id.list_notification);
        notifications = new ArrayList<>(0);
        for(int i=0;i<10;i++){
            notifications.add("notification"+i);
        }

        adapter_notification = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,notifications);
        notification_list.setAdapter(adapter_notification);
    }
}
