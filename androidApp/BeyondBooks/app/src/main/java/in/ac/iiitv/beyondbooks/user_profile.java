package in.ac.iiitv.beyondbooks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class user_profile extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
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
    }
}
