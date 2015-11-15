package in.ac.iiitv.beyondbooks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Frame17 extends AppCompatActivity implements View.OnClickListener {
    ArrayList<String> faculty;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame17);
        submit = (Button) findViewById(R.id.frame17_submit);
        submit.setOnClickListener(this);
        //RequestServer requestServer = new RequestServer();
        //faculty = requestServer.get_faculty();
    }

    @Override
    public void onClick(View v) {

    }
}
