package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Frame11 extends AppCompatActivity {
    private Intent intent;
    private ArrayList<NewlyAdded> uploads;
    private ArrayList<NewlyAdded> reviewed_books;
    ListView list_upload,list_review;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame11);
        //Coded by Anjul Tyagi
        intent = getIntent();
        UserData userData = (UserData)intent.getSerializableExtra("user_data");
        RequestServer requestServer = new RequestServer();
        UserData temp = requestServer.get_activities(/*userData.getId()*/201351010);
        uploads = temp.getUploads();
        reviewed_books = temp.getReviewed();
        //Till here

        list_upload = (ListView) findViewById(R.id.list_upload);
        list_review = (ListView) findViewById(R.id.list_review);

        //fill list_upload
        list_upload.setAdapter(new ArrayAdapter<String>(this,R.layout.frame10_list_view,get_uploads(uploads)));

        list_upload.setAdapter(new ArrayAdapter<String>(this,R.layout.frame10_list_view,get_reviews(reviewed_books)));

    }

    //method to get the uploaded bookname
    private ArrayList<String> get_uploads(ArrayList<NewlyAdded> uploads) {
        ArrayList<String> value = new ArrayList<>(0);
        for(int i=0;i<uploads.size();i++){
            value.add(uploads.get(i).getBook_name());
        }

        return value;
    }

    //method to get the reviewed bookname
    private ArrayList<String> get_reviews(ArrayList<NewlyAdded> reviewed_books) {
        ArrayList<String> value = new ArrayList<>(0);
        for(int i=0;i<uploads.size();i++){
            value.add(reviewed_books.get(i).getBook_name());
        }
        return value;
    }
}
