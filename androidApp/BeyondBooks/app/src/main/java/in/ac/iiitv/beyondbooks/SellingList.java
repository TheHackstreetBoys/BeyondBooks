package in.ac.iiitv.beyondbooks;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SellingList extends AppCompatActivity implements MyDialog.Communicator {
    ArrayList<String> new_array_list;
    ArrayList<Pair<String, Long>> selling_list;
    Integer pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selling_list);
        RequestServer requestServer = new RequestServer();
        new_array_list = new ArrayList<String>();
        System.out.println("here1");
        selling_list = requestServer.get_selling_list(MainActivity.userData.getId());
        System.out.println("here2");
        generate_new_array_list(selling_list);
        System.out.println("here3");
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new_array_list);
        System.out.println("here4");
        listView.setAdapter(arrayAdapter);
        System.out.println("here5");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                displayDialog(view);
                pos = position;
            }
        });
    }
    public void generate_new_array_list(ArrayList<Pair<String, Long>> temp){
        for(int i=0;i<temp.size();i++){
            new_array_list.add(i,temp.get(i).first);
        }
    }
    public void  displayDialog(View v){
        FragmentManager fragmentManager = getFragmentManager();
        MyDialog myDialog = new MyDialog();
        myDialog.show(fragmentManager, "MyDialog");
    }

    @Override
    public void onDialogMessage(String message) {
        if(message.equals("yes")){
            Long temp = selling_list.get(pos).second;
            RequestServer requestServer = new RequestServer();
            Boolean result = requestServer.delete_selling_list_item(MainActivity.userData.getId(), temp);
            if(result){
                selling_list.remove(pos);
                new_array_list.remove(pos);
                Toast.makeText(this, "Book removed from your selling list", Toast.LENGTH_LONG).show();
                ListView listView = (ListView) findViewById(R.id.listView);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new_array_list);
            }
            else{
                Toast.makeText(this, "Some error occured", Toast.LENGTH_LONG).show();
            }
        }
    }
}
