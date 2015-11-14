package in.ac.iiitv.beyondbooks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class HarkatOnForum extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harkat_on_forum);
        Intent intent = getIntent();
        UserData userData = (UserData) intent.getSerializableExtra("user_data");
        RequestServer requestServer = new RequestServer();
        ForumActivities forumActivities = requestServer.get_forum_activities(userData.getId());
    }
}
