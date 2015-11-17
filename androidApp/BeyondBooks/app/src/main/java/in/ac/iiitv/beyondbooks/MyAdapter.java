package in.ac.iiitv.beyondbooks;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by anjul on 16/11/15.
 */
public class MyAdapter extends BaseAdapter{
    Context context;
    ArrayList<Pair<UserData, Float>> task;
    private static LayoutInflater inflater = null;

    public MyAdapter(Context context, ArrayList<Pair<UserData, Float>> task){
        this.context = context;
        this.task = task;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount(){
        return task.size();
    }

    @Override
    public Pair<UserData, Float> getItem(int position){
        return task.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View vi = convertView;
        if(vi == null){
            vi = inflater.inflate(R.layout.sellers_display, null);
        }
        TextView name = (TextView) vi.findViewById(R.id.textView4);
        TextView price = (TextView)vi.findViewById(R.id.textView9);
        name.setText(task.get(position).first.toString());
        price.setText(task.get(position).second.toString());
        return vi;
    }
}
