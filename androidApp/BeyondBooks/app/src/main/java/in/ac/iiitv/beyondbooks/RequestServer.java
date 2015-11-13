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
import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by anjul on 11/11/15.
 */

public class RequestServer {
    private String ip;
    private String address;
    private String output=null;
    RequestServer(){
        ip = "10.100.91.55:80/beyondbooks";
    }

    public Boolean authenticate(Integer id, String password){
        address = "http://"+ip+"/andy_authenticate.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        params.add(new Pair<String, String>("id", id.toString()));
        params.add(new Pair<String, String>("password", password));
        try {
            new Setup().execute(params).get();
//            System.out.println("fucker 2"+output);
            JSONObject is_authenticated_json = new JSONObject(output);
            return Boolean.parseBoolean(is_authenticated_json.getString("result"));
        }catch(JSONException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return false;
    }
    public Boolean authenticate_forget(Integer id){
        address = "http://"+ip+"/andy_authenticate_forget.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        params.add(new Pair<String, String>("id", id.toString()));
        try {
            new Setup().execute(params).get();
            JSONObject is_authenticated_json = new JSONObject(output);
            return Boolean.parseBoolean(is_authenticated_json.getString("result"));
        }catch(JSONException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
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
        try {
            new Setup().execute(params).get();
            JSONObject search_answer = new JSONObject(output);
            JSONArray review = search_answer.getJSONArray("review");
            JSONArray buy_sell = search_answer.getJSONArray("buy_sell");
            JSONArray forum = search_answer.getJSONArray("forum");
            for(int i=0;i<review.length();i++){
                JSONObject cur_book_obj = review.getJSONObject(i);
                String image_link = cur_book_obj.getString("image_link");
                String book_name = cur_book_obj.getString("book_name");
                Float ratings = Float.parseFloat(cur_book_obj.getString("ratings"));
                Long isbn = Long.parseLong(cur_book_obj.getString("isbn"));
                NewlyAdded temp = new NewlyAdded(image_link, book_name, ratings, isbn);
                review_list.add(temp);
            }
            for(int i=0;i<buy_sell.length();i++){
                JSONObject cur_book_obj = buy_sell.getJSONObject(i);
                String image_link = cur_book_obj.getString("image_link");
                String book_name = cur_book_obj.getString("book_name");
                Float ratings = Float.parseFloat(cur_book_obj.getString("ratings"));
                Long isbn = Long.parseLong(cur_book_obj.getString("isbn"));
                NewlyAdded temp = new NewlyAdded(image_link, book_name, ratings, isbn);
                buy_sell_list.add(temp);
            }
            for(int i=0;i<forum.length();i++){
                JSONObject cur_forum_obj = forum.getJSONObject(i);
                String title = cur_forum_obj.getString("title");
                String author = cur_forum_obj.getString("author");
                Integer author_id = Integer.parseInt(cur_forum_obj.getString("author_id"));
                Integer q_id = Integer.parseInt(cur_forum_obj.getString("q_id"));
                ForumOverview temp = new ForumOverview(title, author, author_id, q_id);
                forum_list.add(temp);
            }
            SearchOutputReturn temp = new SearchOutputReturn(review_list, buy_sell_list, forum_list);
            return temp;
        }catch(JSONException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<NewlyAdded> newly_added(){
        address = "http://"+ip+"/andy_newly_added.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        try{
            new Setup().execute(params).get();
            ArrayList<NewlyAdded> newly_added_list = new ArrayList<NewlyAdded>();
            JSONObject search_answer = new JSONObject(output);
            JSONArray newly_added_json = search_answer.getJSONArray("newly_added");
            for(int i=0;i<newly_added_json.length();i++){
                JSONObject cur_book_obj = newly_added_json.getJSONObject(i);
                String image_link = cur_book_obj.getString("image_link");
                String book_name = cur_book_obj.getString("book_name");
                Float ratings = Float.parseFloat(cur_book_obj.getString("ratings"));
                Long isbn = Long.parseLong(cur_book_obj.getString("isbn"));
                NewlyAdded temp = new NewlyAdded(image_link, book_name, ratings, isbn);
                newly_added_list.add(temp);
            }
            return newly_added_list;
        }
        catch (JSONException e){
            e.printStackTrace();;
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<NewlyAdded> top_rated(){
        address = "http://"+ip+"/andy_top_rated.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        try{
            new Setup().execute(params).get();
            ArrayList<NewlyAdded> top_rated_list = new ArrayList<NewlyAdded>();
            JSONObject search_answer = new JSONObject(output);
            JSONArray top_rated_json = search_answer.getJSONArray("top_rated");
            for(int i=0;i<top_rated_json.length();i++){
                JSONObject cur_book_obj = top_rated_json.getJSONObject(i);
                String image_link = cur_book_obj.getString("image_link");
                String book_name = cur_book_obj.getString("book_name");
                Float ratings = Float.parseFloat(cur_book_obj.getString("ratings"));
                Long isbn = Long.parseLong(cur_book_obj.getString("isbn"));
                NewlyAdded temp = new NewlyAdded(image_link, book_name, ratings, isbn);
                top_rated_list.add(temp);
            }
            return top_rated_list;
        }
        catch (JSONException e){
            e.printStackTrace();;
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return null;
    }

    public BookDetails book_page(Long isbn){
        address = "http://"+ip+"/andy_book_page.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        params.add(new Pair<String, String>("isbn", isbn.toString()));
        try {
            new Setup().execute(params).get();
            JSONObject book_page_json = new JSONObject(output);
            Float public_ratings = Float.parseFloat(book_page_json.getString("public_ratings"));
            Float faculty_ratings = Float.parseFloat(book_page_json.getString("faculty_ratings"));
            Float student_ratigns = Float.parseFloat(book_page_json.getString("student_ratings"));
            String about_book = book_page_json.getString("about_book");
            Boolean bookshelf = Boolean.parseBoolean(book_page_json.getString("bookshelf"));
            JSONArray sellers_list = book_page_json.getJSONArray("sellers_list");
            ArrayList<Integer> sellers_id = new ArrayList<Integer>();
            for (int i=0;i<sellers_list.length();i++) {
                JSONObject temp = sellers_list.getJSONObject(i);
                sellers_id.add(Integer.parseInt(temp.getString("id")));
            }
            BookDetails temp = new BookDetails(public_ratings, faculty_ratings, student_ratigns, about_book, bookshelf, isbn, sellers_id );
            return temp;
        }catch(JSONException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return null;
    }

    public Boolean to_shelf(Integer id, Long isbn, Boolean add){
        address = "http://"+ip+"/andy_to_shelf.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        params.add(new Pair<String, String>("user_id", id.toString()));
        params.add(new Pair<String, String>("isbn", isbn.toString()));
        params.add(new Pair<String, String>("add", add.toString()));
        try {
            new Setup().execute(params).get();
            JSONObject book_page_json = new JSONObject(output);
            Boolean done_query = Boolean.parseBoolean(book_page_json.getString("done_query"));
            return done_query;
        }catch(JSONException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return false;
    }

    public Boolean review_submit(Integer user_id,Long isbn, Float ratings, String comment){
        address = "http://"+ip+"/andy_review_submit.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        params.add(new Pair<String, String>("user_id", user_id.toString()));
        params.add(new Pair<String, String>("isbn", isbn.toString()));
        params.add(new Pair<String, String>("ratings", ratings.toString()));
        params.add(new Pair<String, String>("comment", comment));
        try {
            new Setup().execute(params).get();
            JSONObject book_page_json = new JSONObject(output);
            Boolean done_query = Boolean.parseBoolean(book_page_json.getString("done_query"));
            return done_query;
        }catch(JSONException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<String>get_notification(Integer user_id){
        address = "http://"+ip+"/andy_get_notification.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        params.add(new Pair<String, String>("user_id", user_id.toString()));
        try {
            new Setup().execute(params).get();
            ArrayList<String> notification_list = new ArrayList<String>();
            JSONObject search_answer = new JSONObject(output);
            JSONArray notifications_json = search_answer.getJSONArray("notifications");
            for(int i=0;i<notifications_json.length();i++){
                JSONObject cur_book_obj = notifications_json.getJSONObject(i);
                String notification = cur_book_obj.getString("notification");
                notification_list.add(notification);
            }
            return notification_list;
        }catch(JSONException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return null;

    }

    public ArrayList<Long> get_bookshelf(Integer user_id){
        address = "http://"+ip+"/andy_get_bookshelf.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        params.add(new Pair<String, String>("user_id", user_id.toString()));
        try {
            new Setup().execute(params).get();
            ArrayList<Long> book_list = new ArrayList<Long>();
            JSONObject search_answer = new JSONObject(output);
            JSONArray books_json = search_answer.getJSONArray("books");
            for(int i=0;i<books_json.length();i++){
                JSONObject cur_book_obj = books_json.getJSONObject(i);
                Long temp_isbn = Long.parseLong(cur_book_obj.getString("isbn"));
                book_list.add(temp_isbn);
            }
            return book_list;
        }catch(JSONException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return null;
    }

    public void get_activities(UserData cur_user){
        address = "http://"+ip+"/andy_get_activities.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        params.add(new Pair<String, String>("user_id", cur_user.getId().toString()));
        try {
            new Setup().execute(params).get();
            ArrayList<NewlyAdded> uploads_list = new ArrayList<NewlyAdded>();
            JSONObject activities = new JSONObject(output);
            JSONArray uploads = activities.getJSONArray("uploads");
            for(int i=0;i<uploads.length();i++){
                JSONObject cur_book_obj = uploads.getJSONObject(i);
                Long uploaded_book_isbn = Long.parseLong(cur_book_obj.getString("isbn"));
                String uploaded_book_name = cur_book_obj.getString("book_name");
                NewlyAdded temp = new NewlyAdded(null, uploaded_book_name, null, uploaded_book_isbn);
                uploads_list.add(temp);
            }
            cur_user.setUploads(uploads_list);
            JSONArray reviewed = activities.getJSONArray("reviewed");
            ArrayList<NewlyAdded> reviewed_list = new ArrayList<NewlyAdded>();
            for(int i=0;i<reviewed.length();i++){
                JSONObject cur_book = reviewed.getJSONObject(i);
                Long reviewed_book = Long.parseLong(cur_book.getString("isbn"));
                String reviewed_book_name = cur_book.getString("book_name");
                NewlyAdded temp = new NewlyAdded(null, reviewed_book_name, null, reviewed_book);
                reviewed_list.add(temp);
            }
            cur_user.setReviewed(reviewed_list);
//            JSONArray enquired = activities.getJSONArray("enquired");
//            ArrayList<Long> enquired_list = new ArrayList<Long>();
//            for(int i=0;i<enquired.length();i++){
//                JSONObject cur_book = enquired.getJSONObject(i);
//                Long enquired_book = Long.parseLong(cur_book.getString("isbn"));
//                enquired_list.add(enquired_book);
//            }
//            cur_user.setEnquired(enquired_list);
        }catch(JSONException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
    }

    private class Setup extends AsyncTask<ArrayList<Pair<String, String>>, Void, String> {
        HttpURLConnection urlConnection;
        @Override
        protected String doInBackground(ArrayList<Pair<String, String>>... args){
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
            return_method(result.toString());
            return result.toString();
        }
    }
    private void return_method(String return_value){
//        System.out.println("fucker : "+return_value);
        output = return_value;
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
