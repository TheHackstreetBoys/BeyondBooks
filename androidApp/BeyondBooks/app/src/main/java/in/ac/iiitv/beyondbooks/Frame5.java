package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class Frame5 extends AppCompatActivity {
    SearchOutputReturn searchOutputReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame5);
        Intent intent = getIntent();
        searchOutputReturn = (SearchOutputReturn) intent.getSerializableExtra("search_results");
    }
}
