package in.ac.iiitv.beyondbooks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class Frame10 extends AppCompatActivity {

    ListView lv;
    RequestServer rs;
    ArrayList<NewlyAdded> myshelf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame10);
        rs = new RequestServer();
        myshelf = rs.get_bookshelf(201351010);
        lv = (ListView) findViewById(R.id.frame10_bookshelf);
        ArrayList<String> sl = new ArrayList<String>();
        int i;
        for (i=0;i<myshelf.size();i++)
        {
            sl.add(myshelf.get(i).getBook_name());
        }
        System.out.println(sl);
        ArrayAdapter<String> maddapter = new ArrayAdapter<String>(this,R.layout.frame10_list_view,sl);
        lv.setAdapter(maddapter);
    }
}
