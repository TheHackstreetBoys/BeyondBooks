package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Frame15 extends AppCompatActivity {

    private Button next;

    UserData userData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame15);

        userData = (UserData) getIntent().getSerializableExtra("user_data");
        // to submit the question title
        next = (Button) findViewById(R.id.frame15_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next(v);
            }
        });
    }
    public void next(View v){
        EditText editText = (EditText) findViewById(R.id.frame15_question);
        Intent intent = new Intent(this, Frame16.class);
        String question = editText.getText().toString();
        intent.putExtra("user_data",userData);
        intent.putExtra("question", question);
        startActivity(intent);
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
                in = new Intent(this,Frame5.class);
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
            case R.id.option_reviewed_books:
                in = new Intent(this,Frame11.class);
                startActivity(in);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
