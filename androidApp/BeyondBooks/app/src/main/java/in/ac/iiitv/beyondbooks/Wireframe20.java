package in.ac.iiitv.beyondbooks;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Wireframe20 extends Activity {
    String seller_description;
    String book_description;
    Float price;
    Integer age;
    Long isbn;
    UserData userData;
    Integer seller_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wireframe20);
        Intent intent = getIntent();
        seller_description = intent.getStringExtra("seller_description");
        System.out.println("reached here1 "+seller_description);
        book_description = intent.getStringExtra("description");
        System.out.println("reached here2 "+book_description);
        price = Float.parseFloat(intent.getStringExtra("price"));
        System.out.println("reached here3 "+price);
        age = Integer.parseInt(intent.getStringExtra("age"));
        System.out.println("reached here4 "+age);
        isbn = Long.parseLong(intent.getStringExtra("isbn"));
        System.out.println("reached here5 "+isbn);
        userData = MainActivity.userData;
        seller_id = Integer.parseInt(intent.getStringExtra("seller_id"));
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        RequestServer requestServer = new RequestServer();
        Bitmap bitmap = requestServer.getImage(isbn.toString()+".jpg");
        imageView.setImageBitmap(bitmap);
        TextView textViewBookDescription = (TextView)findViewById(R.id.textView7);
        textViewBookDescription.setText(book_description);
        TextView textViewPrice = (TextView)findViewById(R.id.textView6);
        textViewPrice.setText(price.toString());
        TextView textViewAge = (TextView)findViewById(R.id.age2);
        textViewAge.setText(age.toString());
        TextView textViewSeller = (TextView)findViewById(R.id.seller);
        textViewSeller.setText(seller_description);
    }
    public void bid(View v){
        RequestServer requestServer = new RequestServer();
        requestServer.send_enquiry(userData.getId(), seller_id, isbn);
        Toast.makeText(getApplicationContext(), "Notification has been sent to the seller", Toast.LENGTH_LONG).show();
        Intent new_intent = new Intent(this, BookViewPage.class);
        startActivity(new_intent);
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

}
