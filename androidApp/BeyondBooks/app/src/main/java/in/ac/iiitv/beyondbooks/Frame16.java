package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Frame16 extends AppCompatActivity {
    Intent intent;
    String question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame16);
        intent = getIntent();
        question = intent.getStringExtra("question");
    }
    public void next(View v){
        UserData userData = (UserData) intent.getSerializableExtra("user_data");
        intent = new Intent(this, Frame17.class);
        intent.putExtra("question", question);
        EditText editText = (EditText) findViewById(R.id.frame16_question);
        String details = editText.getText().toString();
        intent.putExtra("details", details);
        intent.putExtra("user_data", userData);
        startActivity(intent);
    }
}
