package in.ac.iiitv.beyondbooks;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
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
        Bitmap bitmap = requestServer.getImage("/books_pics/"+isbn.toString()+".jpg");
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
}
