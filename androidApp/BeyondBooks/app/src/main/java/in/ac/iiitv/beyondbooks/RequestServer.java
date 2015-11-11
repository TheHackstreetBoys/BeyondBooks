package in.ac.iiitv.beyondbooks;

//use this http://stackoverflow.com/questions/9767952/how-to-add-parameters-to-httpurlconnection-using-post

import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
/**
 * Created by anjul on 11/11/15.
 */

public class RequestServer {
    private String ip;
    private String address;
    private String output;
    RequestServer(){
        ip = "10.100.1.1:8000";
    }
    public Boolean authenticate(Integer id, String password){
        address = "http://"+ip+"/authenticate.php/id="+id.toString()+":"+"password="+password;
        new Setup().execute();
        try {
            JSONObject is_authenticated_json = new JSONObject(output);
            return Boolean.parseBoolean(is_authenticated_json.getString("result"));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return false;
    }

    public Boolean authenticate_forget(Integer id){
        address = "http://"+ip+"/authenticate_forget/id="+id.toString();
        new Setup().execute();
        try {
            JSONObject is_authenticated_json = new JSONObject(output);
            return Boolean.parseBoolean(is_authenticated_json.getString("result"));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return false;
    }
    public SearchOutputReturn search(String query){
        address = "http://"+ip+"/search/query="+query;
        ArrayList<NewlyAdded> review_list = new ArrayList<NewlyAdded>();
        ArrayList<NewlyAdded> buy_sell_list = new ArrayList<NewlyAdded>();
        ArrayList<ForumOverview> forum_list = new ArrayList<ForumOverview>();
        new Setup().execute();
        new Setup().execute();
        try {
            JSONObject search_answer = new JSONObject(output);
            JSONArray review = search_answer.getJSONArray("review");
            JSONArray buy_sell = search_answer.getJSONArray("buy_sell");
            JSONArray forum = search_answer.getJSONArray("forum");
            for(int i=0;i<review.length();i++){
                JSONObject cur_book_obj = review.getJSONObject(i);
                String image_link = cur_book_obj.getString("image_link");
                String book_name = cur_book_obj.getString("book_name");
                Float ratings = Float.parseFloat(cur_book_obj.getString("ratings"));
                Integer id = Integer.parseInt(cur_book_obj.getString("id"));
                NewlyAdded temp = new NewlyAdded(image_link, book_name, ratings, id);
                review_list.add(temp);
            }
            for(int i=0;i<buy_sell.length();i++){
                JSONObject cur_book_obj = buy_sell.getJSONObject(i);
                String image_link = cur_book_obj.getString("image_link");
                String book_name = cur_book_obj.getString("book_name");
                Float ratings = Float.parseFloat(cur_book_obj.getString("ratings"));
                Integer id = Integer.parseInt(cur_book_obj.getString("id"));
                NewlyAdded temp = new NewlyAdded(image_link, book_name, ratings, id);
                buy_sell_list.add(temp);
            }
            for(int i=0;i<forum.length();i++){
                JSONObject cur_forum_obj = forum.getJSONObject(i);
                String title = cur_forum_obj.getString("title");
                String author = cur_forum_obj.getString("author");
                Integer author_id = Integer.parseInt(cur_forum_obj.getString("author_id"));
                ForumOverview temp = new ForumOverview(title, author, author_id);
                forum_list.add(temp);
            }
            SearchOutputReturn temp = new SearchOutputReturn(review_list, buy_sell_list, forum_list);
            return temp;
        }catch(JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<NewlyAdded> newly_added(){
        address = "http://"+ip+"/newly_added";
        new Setup().execute();
        try{
            ArrayList<NewlyAdded> newly_added_list = new ArrayList<NewlyAdded>();
            JSONObject search_answer = new JSONObject(output);
            JSONArray newly_added_json = search_answer.getJSONArray("newly_added");
            for(int i=0;i<newly_added_json.length();i++){
                JSONObject cur_book_obj = newly_added_json.getJSONObject(i);
                String image_link = cur_book_obj.getString("image_link");
                String book_name = cur_book_obj.getString("book_name");
                Float ratings = Float.parseFloat(cur_book_obj.getString("ratings"));
                Integer id = Integer.parseInt(cur_book_obj.getString("id"));
                NewlyAdded temp = new NewlyAdded(image_link, book_name, ratings, id);
                newly_added_list.add(temp);
            }
            return newly_added_list;
        }
        catch (JSONException e){
            e.printStackTrace();;
        }
        return null;
    }

    public ArrayList<NewlyAdded> top_rated(){
        address = "http://"+ip+"/top_rated";
        new Setup().execute();
        try{
            ArrayList<NewlyAdded> top_rated_list = new ArrayList<NewlyAdded>();
            JSONObject search_answer = new JSONObject(output);
            JSONArray top_rated_json = search_answer.getJSONArray("top_rated");
            for(int i=0;i<top_rated_json.length();i++){
                JSONObject cur_book_obj = top_rated_json.getJSONObject(i);
                String image_link = cur_book_obj.getString("image_link");
                String book_name = cur_book_obj.getString("book_name");
                Float ratings = Float.parseFloat(cur_book_obj.getString("ratings"));
                Integer id = Integer.parseInt(cur_book_obj.getString("id"));
                NewlyAdded temp = new NewlyAdded(image_link, book_name, ratings, id);
                top_rated_list.add(temp);
            }
            return top_rated_list;
        }
        catch (JSONException e){
            e.printStackTrace();;
        }
        return null;
    }
    private class Setup extends AsyncTask<String, String, String> {
        HttpURLConnection urlConnection;
        @Override
        protected String doInBackground(String... args){
            StringBuilder result = new StringBuilder();
            try{
                URL url = new URL(address);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while((line = reader.readLine())!= null){
                    result.append(line);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            finally {
                urlConnection.disconnect();
            }
            output = result.toString();
            return output;
        }
    }
}
