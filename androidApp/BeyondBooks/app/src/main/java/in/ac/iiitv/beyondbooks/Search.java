package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();
        String query = intent.getStringExtra("query");
        RequestServer requestServer = new RequestServer();
        SearchOutputReturn searchOutputReturn = requestServer.search(query);
    }
}
