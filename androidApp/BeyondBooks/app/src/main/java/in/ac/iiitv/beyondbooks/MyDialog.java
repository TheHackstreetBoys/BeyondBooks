package in.ac.iiitv.beyondbooks;

import android.app.Activity;
import android.app.DialogFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MyDialog extends DialogFragment implements View.OnClickListener{
    Communicator communicator;
    Button yesButton, noButton;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        communicator = (Communicator)  activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_dialog, null);
        yesButton = (Button)view.findViewById(R.id.yes);
        noButton = (Button)view.findViewById(R.id.no);
        yesButton.setOnClickListener(this);
        noButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.yes){
            System.out.println("Entered yes");
            communicator.onDialogMessage("yes");
            dismiss();
        }
        else{
            communicator.onDialogMessage("no");
            dismiss();
        }
    }
    interface Communicator{
        public void onDialogMessage(String message);
    }
}
