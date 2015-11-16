package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Search extends AppCompatActivity {

    private EditText text_search;
    private Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);



        search = (Button) findViewById(R.id.button_search);


    }
    public void serch_all(View v) {
        RequestServer requestServer = new RequestServer();
        EditText et = (EditText) findViewById(R.id.search_text);

        //requestServer.g

    }
}
