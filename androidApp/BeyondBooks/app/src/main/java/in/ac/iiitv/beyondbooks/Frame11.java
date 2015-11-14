package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class Frame11 extends AppCompatActivity {
    private Intent intent;
    private ArrayList<NewlyAdded> uploads;
    private ArrayList<NewlyAdded> reviewed_books;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame11);
        //Coded by Anjul Tyagi
        intent = getIntent();
        UserData userData = (UserData)intent.getSerializableExtra("user_data");
        RequestServer requestServer = new RequestServer();
        UserData temp = requestServer.get_activities(userData.getId());
        uploads = temp.getUploads();
        reviewed_books = temp.getReviewed();
        //Till here
    }
}
