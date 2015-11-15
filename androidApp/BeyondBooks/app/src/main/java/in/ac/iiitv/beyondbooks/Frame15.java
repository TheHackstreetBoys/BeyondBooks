package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Frame15 extends AppCompatActivity {

    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame15);

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
        intent.putExtra("question", question);
        startActivity(intent);
    }
}
