package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Frame14 extends AppCompatActivity {
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame14);
        intent = getIntent();
        Integer q_id = Integer.parseInt(intent.getStringExtra("q_id"));
        RequestServer requestServer = new RequestServer();
        ForumDetails forumDetails = requestServer.forumDetails(q_id);
    }
}
