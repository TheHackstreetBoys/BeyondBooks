package in.ac.iiitv.beyondbooks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class Frame10 extends AppCompatActivity {

    ListView lv;
    RequestServer rs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame10);
        rs = new RequestServer();
        rs.get_bookshelf(201351010);

        lv = (ListView) findViewById(R.id.frame10_bookshelf);

    }
}
