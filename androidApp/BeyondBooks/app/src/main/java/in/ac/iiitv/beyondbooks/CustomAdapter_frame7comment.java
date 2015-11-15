package in.ac.iiitv.beyondbooks;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import in.ac.iiitv.beyondbooks.R;

public class CustomAdapter_frame7comment extends BaseAdapter
{
    Activity context;
    ArrayList<String> commentby;
    ArrayList<String> comments;

    public CustomAdapter_frame7comment (Activity context, ArrayList<String> commentby, ArrayList<String> comments){
        super();
        this.context = context;
        this.comments = comments;
        this.commentby = commentby;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return commentby.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    private class ViewHolder {
        TextView commentby;
        TextView comment;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.custom_list_frame7_comment, null);
            holder = new ViewHolder();
            holder.commentby = (TextView) convertView.findViewById(R.id.commentby);
            holder.comment = (TextView) convertView.findViewById(R.id.comment);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.commentby.setText(commentby.get(position));
        holder.comment.setText(comments.get(position));

        return convertView;
    }

}