//package in.ac.iiitv.beyondbooks;
//
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//
//public class Wireframe13 extends AppCompatActivity implements AdapterView.OnItemClickListener {
//    ArrayList<ForumOverview> top_rated, recent_discussions;
//    ListView toprated,recent;
//    ArrayAdapter<String> adapter_toprated,adapter_recent;
//    private UserData userData;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_wireframe13);
//        userData = (UserData)getIntent().getSerializableExtra("user_data");
//        RequestServer requestServer = new RequestServer();
//        top_rated = requestServer.top_rated_discussions();
//        recent_discussions = requestServer.recent_discussions();
//
//        //Button to start a new discussion
//        Button newdis = (Button) findViewById(R.id.startdiscussion);
//        newdis.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //intent should be checked
//                Intent in = new Intent(getApplicationContext(), Frame15.class);
//                in.putExtra("user_data", userData);
//
//                startActivity(in);
//            }
//        });
//
//
//        // Get ListView object from xml
//        toprated = (ListView) findViewById(R.id.topratedlist);
//
//        // Defined Array values to show in ListView
//        ArrayList<String> values_toprated = new ArrayList<String>(0) ;
//        for(int i=0;i<top_rated.size();i++){
//            values_toprated.add(top_rated.get(i).getTitle()+"\n"+top_rated.get(i).getAuthor());
//        }
//        adapter_toprated = new ArrayAdapter<String>(this,R.layout.frame10_list_view, values_toprated);
//        // Assign adapter to ListView
//        toprated.setAdapter(adapter_toprated);
//
//
//        //For recent discussion
//        // Get ListView object from xml
//        recent = (ListView) findViewById(R.id.recentlist);
//        // Defined Array values to show in ListView
//        ArrayList<String> values_recent = new ArrayList<String>(0);
//        for (int i=0;i<recent_discussions.size();i++){
//            values_recent.add(recent_discussions.get(i).getTitle()+"\n"+recent_discussions.get(i).getAuthor());
//        }
//        adapter_recent = new ArrayAdapter<String>(this,R.layout.frame10_list_view, values_recent);
//        // Assign adapter to ListView
//        recent.setAdapter(adapter_recent);
//
//        toprated.setOnItemClickListener(this);
//        recent.setOnItemClickListener(this);
//
//    }
//
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//        int rid = parent.getId();
//        Intent in;
//        switch (rid)
//        {
//            case R.id.topratedlist:
//                Toast.makeText(this,top_rated.get(position).getAuthor(),Toast.LENGTH_LONG).show();
//                //intent should be checked
//                in = new Intent(getApplicationContext(), Frame14.class);
//
//                in.putExtra("q_id", top_rated.get(position).getQ_id().toString());
//                in.putExtra("user_data",userData);
//                startActivity(in);
//                break;
//            case R.id.recentlist:
//                Toast.makeText(this,recent_discussions.get(position).getAuthor(),Toast.LENGTH_LONG).show();
//                in = new Intent(getApplicationContext(), Frame14.class);
//                System.out.println(recent_discussions.get(position).getQ_id().toString());
//                in.putExtra("q_id",recent_discussions.get(position).getQ_id().toString());
//                in.putExtra("user_data", userData);
//                startActivity(in);
//                break;
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        Intent in;
//        switch(id)
//        {
//            case R.id.option_search:
//                in = new Intent(this,Search.class);
//                startActivity(in);
//                break;
//            case R.id.option_home:
//
//                in = new Intent(this, BookViewPage.class);
//                startActivity(in);
//                break;
//            case R.id.option_user_profile:
//                in = new Intent(this, Wireframe8.class);
//                startActivity(in);
//                break;
//            case R.id.option_activity_on_forum:
//                in = new Intent(this,Wireframe12.class);
//                startActivity(in);
//                break;
//            case R.id.option_book_shelf:
//                in = new Intent(this,Frame10.class);
//                startActivity(in);
//                break;
//            case R.id.option_forum:
//                in = new Intent(this,Wireframe13.class);
//
//                startActivity(in);
//                break;
//            case R.id.option_logout:
//                in = new Intent(this,MainActivity.class);
//                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(in);
//                break;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//}
