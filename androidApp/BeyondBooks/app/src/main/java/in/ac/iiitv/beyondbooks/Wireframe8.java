package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Wireframe8 extends AppCompatActivity {

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
//        changepass = (TextView) findViewById(R.id.changepass);
        userData = (UserData)intent.getSerializableExtra("user_data");
//        userData.setForumActivities(requestServer.get_forum_activities(userData.getId()));
//        UserData temp = requestServer.get_user_name_image(userData.getId());
//        userData.setImage_link(temp.getImage_link());
//        userData.setUser_name(temp.getUser_name());
//        UserData temp2 = requestServer.get_activities(userData.getId());
//        userData.setReviewed(temp2.getReviewed());
//        userData.setUploads(temp2.getUploads());
        userData.setUser_name(requestServer.get_user_name(userData.getId()));
<<<<<<< HEAD

=======
        notifications = requestServer.get_notification(userData.getId());
        System.out.println("notifications: "+notifications);
>>>>>>> e47df7bf6608047b5da0f728dd1df414a79ee07c
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

=======
        Bitmap temp = requestServer.getImage(userData.getId().toString() + "_dp.jpg");
        System.out.println("temp : "+temp);
        user_image.setImageBitmap(temp);
>>>>>>> e47df7bf6608047b5da0f728dd1df414a79ee07c
        //set username
        username = (TextView) findViewById(R.id.user_name);
        username.setText(userData.getUser_name());
        //set user id
        userid = (TextView) findViewById(R.id.user_id);
        userid.setText(userData.getId().toString());
        //change password method
//        changepass = (TextView) findViewById(R.id.changepass);
//        changepass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EditText new_password = (EditText) findViewById(R.id.changepassword);
//                new_password.setVisibility(View.VISIBLE);
//                Button set_pass = (Button) findViewById(R.id.setpassword);
//                set_pass.setVisibility(View.VISIBLE);
//            }
//        });

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
//    public void set_new_password(View v){
//
//    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selected_image = data.getData();
            user_image.setImageURI(selected_image);
        }
    }
}
