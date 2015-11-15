package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Frame15 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame15);
    }
    public void next(View v){
        EditText editText = (EditText) findViewById(R.id.frame15_question);
        Intent intent = new Intent(this, Frame16.class);
        String question = editText.getText().toString();
        intent.putExtra("question", question);
        startActivity(intent);
    }
}
