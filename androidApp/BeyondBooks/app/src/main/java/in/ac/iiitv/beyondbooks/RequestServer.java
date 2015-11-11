package in.ac.iiitv.beyondbooks;

//use this http://stackoverflow.com/questions/9767952/how-to-add-parameters-to-httpurlconnection-using-post

import android.os.AsyncTask;
import android.util.Pair;
import android.widget.ArrayAdapter;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
/**
 * Created by anjul on 11/11/15.
 */

public class RequestServer {
    private String ip;
    private String address;
    private String output;
    private ArrayList<NewlyAdded> newly_added_list;
    private ArrayList<NewlyAdded> top_rated_list;
    RequestServer(){
        ip = "10.100.91.55:80";
        newly_added_list = new ArrayList<NewlyAdded>();
        top_rated_list = new ArrayList<NewlyAdded>();
    }
    public Boolean authenticate(Integer id, String password){
        address = "http://"+ip+"/andy_authenticate.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        params.add(new Pair<String, String>("id", id.toString()));
        params.add(new Pair<String, String>("password", password));
        new Setup().execute(params);
        try {
            JSONObject is_authenticated_json = new JSONObject(output);
            return Boolean.parseBoolean(is_authenticated_json.getString("result"));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return false;
    }

    public Boolean authenticate_forget(Integer id){
        address = "http://"+ip+"/andy_authenticate_forget.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        params.add(new Pair<String, String>("id", id.toString()));
        new Setup().execute(params);
        try {
            JSONObject is_authenticated_json = new JSONObject(output);
            return Boolean.parseBoolean(is_authenticated_json.getString("result"));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return false;
    }
    public SearchOutputReturn search(String query){
        address = "http://"+ip+"/andy_search.php";
        ArrayList<NewlyAdded> review_list = new ArrayList<NewlyAdded>();
        ArrayList<NewlyAdded> buy_sell_list = new ArrayList<NewlyAdded>();
        ArrayList<ForumOverview> forum_list = new ArrayList<ForumOverview>();
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        params.add(new Pair<String, String>("query", query));
        new Setup().execute(params);
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
        address = "http://"+ip+"/andy_newly_added.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        new Setup().execute(params);
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
            this.newly_added_list = newly_added_list;
            return newly_added_list;
        }
        catch (JSONException e){
            e.printStackTrace();;
        }
        return null;
    }

    public ArrayList<NewlyAdded> top_rated(){
        address = "http://"+ip+"/andy_top_rated.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        new Setup().execute(params);
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
            this.top_rated_list = top_rated_list;
            return top_rated_list;
        }
        catch (JSONException e){
            e.printStackTrace();;
        }
        return null;
    }


    private class Setup extends AsyncTask<ArrayList<Pair<String, String>>, String, ArrayList<Pair<String, String>>> {
        HttpURLConnection urlConnection;
        @Override
        protected ArrayList<Pair<String, String>> doInBackground(ArrayList<Pair<String, String>>... args){
            StringBuilder result = new StringBuilder();
            try{
                URL url = new URL(address);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                ArrayList<Pair<String, String>> to_send = args[0];
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getQuery(to_send));
                writer.flush();
                writer.close();
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
            return null;
        }
    }
    private String getQuery(ArrayList<Pair<String, String>> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (int i=0;i<params.size();i++) {
            if (i!=0)
                result.append("&");

            result.append(URLEncoder.encode(params.get(i).first, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(params.get(i).second, "UTF-8"));
        }

        return result.toString();
    }
}
