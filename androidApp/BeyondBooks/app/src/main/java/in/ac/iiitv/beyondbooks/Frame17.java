package in.ac.iiitv.beyondbooks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class Frame17 extends AppCompatActivity {
    ArrayList<String> faculty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame17);
        RequestServer requestServer = new RequestServer();
        faculty = requestServer.get_faculty();
    }
}
