//package in.ac.iiitv.beyondbooks;
//
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//
//import java.util.ArrayList;
//
//public class Frame10 extends AppCompatActivity implements AdapterView.OnItemClickListener {
//    private Intent intent;
//    ListView lv;
//    RequestServer rs;
//    ArrayList<NewlyAdded> myshelf;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_frame10);
//        rs = new RequestServer();
//        intent = getIntent();
//
//
//        myshelf = rs.get_bookshelf(MainActivity.userData.getId());
//
//        lv = (ListView) findViewById(R.id.frame10_bookshelf);
//        ArrayList<String> sl = new ArrayList<String>();
//        int i;
//        for (i=0;i<myshelf.size();i++)
//        {
//            sl.add(myshelf.get(i).getBook_name());
//        }
//        System.out.println(sl);
//        ArrayAdapter<String> maddapter = new ArrayAdapter<String>(this,R.layout.frame10_list_view,sl);
//        lv.setAdapter(maddapter);
//        lv.setOnItemClickListener(this);
//    }
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
//                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
//                startActivity(in);
//                break;
//            case R.id.option_sell:
//                in = new Intent(this,SellBook.class);
//                startActivity(in);
//                break;
//            case R.id.option_sell_list:
//                in = new Intent(this,SellingList.class);
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
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        long isbn = myshelf.get(position).getId();
//        Intent in = new Intent(this,Wireframe7.class);
//        in.putExtra("isbn",(new Long(isbn)).toString() );
//        in.putExtra("user_data",MainActivity.userData);
//        startActivity(in);
//    }
//}
