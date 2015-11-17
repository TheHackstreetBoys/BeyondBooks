package in.ac.iiitv.beyondbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {
    EditText pass1 , pass2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        pass1 = (EditText)findViewById(R.id.editText);
        pass2 = (EditText)findViewById(R.id.editText2);
    }
    public void set_new_password(View v){
        String pas1 = pass1.getText().toString();
        String pas2 = pass2.getText().toString();
        if(pas1.equals(pas2) && pas1.length() != 0){
                RequestServer requestServer = new RequestServer();
                Boolean result = requestServer.set_new_password(MainActivity.userData.getId(), pas1);
                if(result){
                    Toast.makeText(this, "New password set successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, Wireframe8.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(this, "Some error occured", Toast.LENGTH_LONG).show();
                }
        }
        else{
            Toast.makeText(this, "Set some password", Toast.LENGTH_LONG).show();
        }
    }


}
