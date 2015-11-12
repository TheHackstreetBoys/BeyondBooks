package in.ac.iiitv.beyondbooks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class wireframe8 extends AppCompatActivity {

    ListView notification_list ;
    ArrayList<String> notifications;
    ArrayAdapter<String> adapter_notification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wireframe8);

        notification_list = (ListView) findViewById(R.id.list_notification);
        notifications = new ArrayList<>(0);
        for(int i=0;i<10;i++){
            notifications.add("notification"+i);
        }

        adapter_notification = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,notifications);
        notification_list.setAdapter(adapter_notification);
    }
}
