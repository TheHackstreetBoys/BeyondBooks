package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Wireframe8 extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayList<String> notifications;
    private ImageView user_image;
    private ListView notification_list ;
    private TextView username,userid,changepass;
    private ArrayAdapter<String> adapter_notification;
    private Button uploadimage;


    UserData userData;
    private static final int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wireframe8);
        //Added by Anjul Tyagi
        notifications = new ArrayList<String>();
        RequestServer requestServer = new RequestServer();
        Intent intent = getIntent();
        changepass = (TextView) findViewById(R.id.changepass);
        userData = MainActivity.userData;
//        userData.setForumActivities(requestServer.get_forum_activities(userData.getId()));
//        UserData temp = requestServer.get_user_name_image(userData.getId());
//        userData.setImage_link(temp.getImage_link());
//        userData.setUser_name(temp.getUser_name());
//        UserData temp2 = requestServer.get_activities(userData.getId());
//        userData.setReviewed(temp2.getReviewed());
//        userData.setUploads(temp2.getUploads());
        userData.setUser_name(requestServer.get_user_name(userData.getId()));

        notifications = requestServer.get_notification(userData.getId());
        System.out.println("notifications: "+notifications);
        //Till here... userData contains every information about the user. Use it to populate the page.


        //set image of the user
        /*user_image = (ImageView) findViewById(R.id.user_image);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put(userData.getImage_link(), R.drawable.user_image);
        user_image.setImageResource(map.get(userData.getImage_link()));
        */

        user_image = (ImageView) findViewById(R.id.user_image);
<<<<<<< HEAD
        user_image.setImageBitmap(requestServer.getImage(userData.getId().toString() + "_dp.jpg"));
        Bitmap temp = requestServer.getImage(userData.getId().toString() + "_dp.jpg");
=======
        user_image.setImageBitmap(requestServer.getDpImage("/pictures/"+userData.getId().toString() + "_dp.jpg"));
        Bitmap temp = requestServer.getDpImage("/pictures/"+userData.getId().toString() + "_dp.jpg");
>>>>>>> a0de765ee859695a0978aea2c4fc654a99593074
        System.out.println("temp : "+temp);
        user_image.setImageBitmap(temp);
        //set username
        username = (TextView) findViewById(R.id.user_name);
        username.setText(userData.getUser_name());
        //set user id
        userid = (TextView) findViewById(R.id.user_id);
        userid.setText(userData.getId().toString());

<<<<<<< HEAD
        notification_list.setOnItemClickListener(this);
=======

>>>>>>> a0de765ee859695a0978aea2c4fc654a99593074
        //change password method
//        changepass = (TextView) findViewById(R.id.changepass);
        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText new_password = (EditText) findViewById(R.id.changepassword);
                new_password.setVisibility(View.VISIBLE);
                Button set_pass = (Button) findViewById(R.id.setpassword);
                set_pass.setVisibility(View.VISIBLE);
            }
        });

        //button upload image

        uploadimage = (Button) findViewById(R.id.upload_image);
        uploadimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO set the image picker to choose the image and then sent it to the server

            }
        });

//        uploadimage = (Button) findViewById(R.id.upload_image);
//        uploadimage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                set_dp(v);
//            }
//        });
        //set notifications (Already there is a arraylist for arrayadapter)

        notifications = requestServer.get_notification(userData.getId());
        notification_list = (ListView) findViewById(R.id.notification_list);
        adapter_notification = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,notifications);
        notification_list.setAdapter(adapter_notification);
        System.out.println("Reaching here ....");
    }
    public void set_dp(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RESULT_LOAD_IMAGE);
        RequestServer requestServer = new RequestServer();
        Bitmap bitmap_to_send = ((BitmapDrawable)user_image.getDrawable()).getBitmap();
        requestServer.setImage(bitmap_to_send, userData.getId().toString());
    }
    public void set_new_password(View v){
        TextView textView = (TextView)findViewById(R.id.changepassword);
        String new_pass = textView.getText().toString();
        if(new_pass.length() == 0){
            Toast.makeText(this, "Set some password", Toast.LENGTH_LONG).show();
        }
        else{
            RequestServer requestServer = new RequestServer();
            Boolean result = requestServer.set_new_password(MainActivity.userData.getId(), new_pass);
            if(result){
                Toast.makeText(this, "New password set successful", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "Some error occured", Toast.LENGTH_LONG).show();
            }
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selected_image = data.getData();
            user_image.setImageURI(selected_image);
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
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
            case R.id.option_sell:
                in = new Intent(this,SellBook.class);
                startActivity(in);
                break;
            case R.id.option_sell_list:
                in = new Intent(this,SellingList.class);
                startActivity(in);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
