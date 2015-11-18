package in.ac.iiitv.beyondbooks;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< HEAD
=======
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
>>>>>>> 14de733b003cb9d6fd14d1046d4577bbe5485505
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SellingList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selling_list);
        RequestServer requestServer = new RequestServer();
        ArrayList<String> selling_list = requestServer.get_selling_list(MainActivity.userData.getId());
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, selling_list);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(getApplicationContext())
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }
<<<<<<< HEAD
=======
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Intent in;
        switch(id)
        {
            case R.id.option_search:
                in = new Intent(this,Search.class);
                startActivity(in);
                break;
            case R.id.option_home:

                in = new Intent(this, BookViewPage.class);
                startActivity(in);
                break;
            case R.id.option_user_profile:
                in = new Intent(this, Wireframe8.class);
                startActivity(in);
                break;
            case R.id.option_activity_on_forum:
                in = new Intent(this,Wireframe12.class);
                startActivity(in);
                break;
            case R.id.option_book_shelf:
                in = new Intent(this,Frame10.class);
                startActivity(in);
                break;
            case R.id.option_forum:
                in = new Intent(this,Wireframe13.class);
                startActivity(in);
                break;
            case R.id.option_logout:
                in = new Intent(this,MainActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
>>>>>>> 14de733b003cb9d6fd14d1046d4577bbe5485505
}
