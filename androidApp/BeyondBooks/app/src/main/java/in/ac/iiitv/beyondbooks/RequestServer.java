package in.ac.iiitv.beyondbooks;

//use this http://stackoverflow.com/questions/9767952/how-to-add-parameters-to-httpurlconnection-using-post

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.util.Pair;
import android.widget.ArrayAdapter;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.DOMError;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

/**
 * Created by anjul on 11/11/15.
 */

public class RequestServer {
    private String ip;
    private String address;
    private String output=null;
    String image_link;
    Bitmap image;
    RequestServer(){
        ip = "10.100.91.55/beyondbooks";
        image_link = "10.100.91.55/pictures";
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
        params.add(new Pair<String, String>("user_id", id.toString()));
        try {
            new Setup().execute(params).get();
            System.out.println("Output " + output);
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
    //

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
            for(int i=0;i<top_rated_json.length();i++) {
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

    public BookDetails book_page(Long isbn, Integer user_id){
        address = "http://"+ip+"/andy_book_page.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        params.add(new Pair<String, String>("isbn", isbn.toString()));
        params.add(new Pair<String, String>("user_id", user_id.toString()));
        try {
            new Setup().execute(params).get();
            JSONObject book_page_json = new JSONObject(output);
            Float public_ratings = Float.parseFloat(book_page_json.getString("public_ratings"));
            Float faculty_ratings = Float.parseFloat(book_page_json.getString("faculty_ratings"));
            Float student_ratigns = Float.parseFloat(book_page_json.getString("student_ratings"));
            String about_book = book_page_json.getString("about_book");
            Boolean bookshelf = Boolean.parseBoolean(book_page_json.getString("bookshelf"));
            JSONArray sellers_list = book_page_json.getJSONArray("sellers_list");
            ArrayList<Pair<UserData, Float>> sellers_id = new ArrayList<Pair<UserData, Float>>();
            for (int i=0;i<sellers_list.length();i++) {
                UserData userData = new UserData(user_id);
                JSONObject temp = sellers_list.getJSONObject(i);
                userData.setId(Integer.parseInt(temp.getString("seller_id")));
                userData.setUser_name(temp.getString("seller_name"));
                Float price = Float.parseFloat(temp.getString("price"));
                sellers_id.add(new Pair<UserData, Float>(userData, price));
            }
            ArrayList<Pair<String, String>> comments_list = new ArrayList<Pair<String, String>>();
            JSONArray comments_json = book_page_json.getJSONArray("comments");
            for(int i=0;i<comments_json.length();i++){
                JSONObject comment = comments_json.getJSONObject(i);
                String commentor_name = comment.getString("commentor_name");
                String text = comment.getString("text");
                comments_list.add(new Pair<String, String>(text, commentor_name));
            }
            BookDetails temp = new BookDetails(public_ratings, faculty_ratings, student_ratigns, about_book, bookshelf, isbn, sellers_id, comments_list );
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
                String notification = cur_book_obj.toString();
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

    public ArrayList<NewlyAdded> get_bookshelf(Integer user_id) {
        address = "http://"+ip+"/andy_get_bookshelf.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        params.add(new Pair<String, String>("user_id", user_id.toString()));
        try {
            new Setup().execute(params).get();
            ArrayList<NewlyAdded> book_list = new ArrayList<NewlyAdded>();
            JSONObject search_answer = new JSONObject(output);
            JSONArray books_json = search_answer.getJSONArray("books");
            for(int i=0;i<books_json.length();i++){
                JSONObject cur_book_obj = books_json.getJSONObject(i);
                Long temp_isbn = Long.parseLong(cur_book_obj.getString("isbn"));
                String temp_name = cur_book_obj.getString("book_name");
                NewlyAdded tempo = new NewlyAdded(null, temp_name, null, temp_isbn);
                book_list.add(tempo);
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

    public UserData get_activities (Integer user_id){
        address = "http://"+ip+"/andy_get_activities.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        params.add(new Pair<String, String>("user_id", user_id.toString()));
        UserData cur_user = new UserData(user_id);
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
            return cur_user;
        }catch(JSONException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return null;
    }

    public ForumActivities get_forum_activities(Integer user_id){
        address = "http://"+ip+"/andy_get_forum_activities.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        params.add(new Pair<String, String>("user_id", user_id.toString()));
        try {
            new Setup().execute(params).get();
            ArrayList<ForumOverview> questions_started = new ArrayList<ForumOverview>();
            JSONObject activities = new JSONObject(output);
            JSONArray uploads = activities.getJSONArray("questions_started");
            for(int i=0;i<uploads.length();i++){
                JSONObject cur_book_obj = uploads.getJSONObject(i);
                String title = cur_book_obj.getString("title");
                Integer author_id = Integer.parseInt(cur_book_obj.getString("author_id"));
                Integer q_id = Integer.parseInt(cur_book_obj.getString("q_id"));
                ForumOverview temp = new ForumOverview(title, null, author_id, q_id);
                System.out.println("maakichut : "+author_id+ " "+q_id);
                questions_started.add(temp);
            }
            JSONArray reviewed = activities.getJSONArray("commented");
            ArrayList<Comments> commented_list = new ArrayList<Comments>();
            for(int i=0;i<reviewed.length();i++){
                JSONObject cur_book = reviewed.getJSONObject(i);
                String text = cur_book.getString("text");
                Integer q_id = Integer.parseInt(cur_book.getString("q_id"));
                String q_title = cur_book.getString("q_title");
                String comment_id = cur_book.getString("comment_id");
                Comments temp = new Comments(user_id, text, comment_id, q_id, q_title);
                commented_list.add(temp);
            }
            ForumActivities forumActivities = new ForumActivities(questions_started, commented_list);
            return forumActivities;
        }catch(JSONException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<ForumOverview> top_rated_discussions(){
        address = "http://"+ip+"/andy_top_rated_discussions.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        try {
            new Setup().execute(params).get();
            ArrayList<ForumOverview> top_discussions_list = new ArrayList<ForumOverview>();
            JSONObject activities = new JSONObject(output);
            JSONArray uploads = activities.getJSONArray("top_rated");
            for(int i=0;i<uploads.length();i++){
                JSONObject cur_book_obj = uploads.getJSONObject(i);
                String title = cur_book_obj.getString("title");
                String author = cur_book_obj.getString("author");
                Integer author_id = Integer.parseInt(cur_book_obj.getString("author_id"));
                Integer q_id = Integer.parseInt(cur_book_obj.getString("q_id"));
                ForumOverview temp = new ForumOverview(title, author, author_id, q_id);
                top_discussions_list.add(temp);
            }
            System.out.println("behenchod : " + top_discussions_list);
            return top_discussions_list;
        }catch(JSONException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<ForumOverview> recent_discussions(){
        address = "http://"+ip+"/andy_recent_discussions.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        try {
            new Setup().execute(params).get();
            ArrayList<ForumOverview> recent_discussions_list = new ArrayList<ForumOverview>();
            JSONObject activities = new JSONObject(output);
            JSONArray uploads = activities.getJSONArray("recent");
            for(int i=0;i<uploads.length();i++){
                JSONObject cur_book_obj = uploads.getJSONObject(i);
                String title = cur_book_obj.getString("title");
                String author = cur_book_obj.getString("author");
                Integer author_id = Integer.parseInt(cur_book_obj.getString("author_id"));
                Integer q_id = Integer.parseInt(cur_book_obj.getString("q_id"));
                ForumOverview temp = new ForumOverview(title, author, author_id, q_id);
                recent_discussions_list.add(temp);
            }
            return recent_discussions_list;
        }catch(JSONException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return null;
    }

    public ForumDetails forumDetails(Integer q_id){
        address = "http://"+ip+"/andy_forum_details.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        params.add(new Pair<String, String>("q_id", q_id.toString()));
        try {
            new Setup().execute(params).get();
            JSONObject jsonObject = new JSONObject(output);
            String title = jsonObject.getString("title");
            String author_name = jsonObject.getString("author_name");
            Integer author_id = Integer.parseInt(jsonObject.getString("author_id"));
            Integer forum_id = q_id;
            String details = jsonObject.getString("details");
            JSONArray jsonArray = jsonObject.getJSONArray("comments");
            ArrayList<Comments> comments = new ArrayList<Comments>();
            for(int i=0;i<jsonArray.length();i++){
                JSONObject comment_json = jsonArray.getJSONObject(i);
                Integer comment_user_id = Integer.parseInt(comment_json.getString("user_id"));
                String comment_text = comment_json.getString("text");
                System.out.print(" dsfa "+comment_json.get("comment_id"));
                String comment_id = comment_json.getString("comment_id");
                Comments temp = new Comments(comment_user_id, comment_text, comment_id, forum_id, title);
                //String user_name = comment_json.getString("user_name");
                //temp.setUser_name(user_name);
                comments.add(temp);
            }
            ForumDetails to_return = new ForumDetails(title, author_name, forum_id, author_id, comments);
            to_return.setDetails(details);
            return to_return;
        }catch(JSONException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return null;
    }

    public Boolean add_forum_question(ForumDetails forumDetails){
        address = "http://"+ip+"/andy_add_forum_question.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        params.add(new Pair<String, String>("title", forumDetails.getTitle()));
        params.add(new Pair<String, String>("author_name", forumDetails.getAuthor_name()));
        params.add(new Pair<String, String>("author_id", forumDetails.getAuthor_id().toString()));
        params.add(new Pair<String, String>("details", forumDetails.getDetails()));
        params.add(new Pair<String, String>("tags", forumDetails.getTags()));
        params.add(new Pair<String, String>("faculty_tags", forumDetails.getFaculty_tags()));
        try{
            new Setup().execute(params).get();
            JSONObject jsonObject = new JSONObject(output);
            Boolean result = Boolean.parseBoolean(jsonObject.getString("result"));
            return result;
        }catch(JSONException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return false;
    }

    public Boolean add_book(Long isbn, Integer user_id){
        address = "http://"+ip+"/andy_add_book.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        params.add(new Pair<String, String>("isbn", isbn.toString()));
        params.add(new Pair<String, String>("user_id", user_id.toString()));
        try{
            new Setup().execute(params).get();
            JSONObject jsonObject = new JSONObject(output);
            Boolean result = Boolean.parseBoolean(jsonObject.getString("result"));
            return result;
        }catch(JSONException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return false;
    }

    public Boolean sell_book(Long isbn, Integer user_id, Float age, Float price, String description){
        address = "http://"+ip+"/andy_sell_book.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        params.add(new Pair<String, String>("isbn", isbn.toString()));
        params.add(new Pair<String, String>("user_id", user_id.toString()));
        params.add(new Pair<String, String>("age", age.toString()));
        params.add(new Pair<String, String>("price", price.toString()));
        params.add(new Pair<String, String>("description",description));
        try{
            new Setup().execute(params).get();
            JSONObject jsonObject = new JSONObject(output);
            Boolean result = Boolean.parseBoolean(jsonObject.getString("result"));
            return result;
        }catch(JSONException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return false;
    }

    public Boolean send_enquiry(Integer buyer_id, Integer seller_id, Long isbn){
        address = "http://"+ip+"/andy_enquiry.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        params.add(new Pair<String, String>("buyer_id", buyer_id.toString()));
        params.add(new Pair<String, String>("seller_id", seller_id.toString()));
        params.add(new Pair<String, String>("isbn", isbn.toString()));
        try{
            new Setup().execute(params).get();
            JSONObject jsonObject = new JSONObject(output);
            Boolean result = Boolean.parseBoolean(jsonObject.getString("result"));
            return result;
        }catch(JSONException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return false;
    }

    public String identify_book(Long isbn){
        address = "http://"+ip+"/andy_identify_book.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        params.add(new Pair<String, String>("isbn", isbn.toString()));
        try{
            new Setup().execute(params).get();
            JSONObject jsonObject = new JSONObject(output);
            String result = jsonObject.getString("book_name");
            return result;
        }catch(JSONException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return null;
    }

    public String get_user_name(Integer id){
        address = "http://"+ip+"/andy_get_user_data.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        params.add(new Pair<String, String>("user_id", id.toString()));
        try{
            new Setup().execute(params).get();
            JSONObject jsonObject = new JSONObject(output);
            String user_name = jsonObject.getString("user_name");
            return user_name;
        }catch(JSONException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return null;
    }


    public ArrayList<String> get_faculty(){
        address = "http://"+ip+"/andy_get_faculty.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        try{
            new Setup().execute(params).get();
            ArrayList<String> temp = new ArrayList<String>();
            JSONObject jsonObject = new JSONObject(output);
            JSONArray faculty_list = jsonObject.getJSONArray("faculties");
            for (int i=0;i<faculty_list.length();i++){
                temp.add(faculty_list.getString(i));

            }
            System.out.println("lund : " + temp);
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

    public Boolean send_comment(Integer user_id, Integer q_id, String text){
        address = "http://"+ip+"/andy_send_comment.php";
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        params.add(new Pair<String, String>("user_id", user_id.toString()));
        params.add(new Pair<String, String>("q_id", q_id.toString()));
        params.add(new Pair<String, String>("text", text));
        try{
            new Setup().execute(params).get();
            JSONObject jsonObject = new JSONObject(output);
            Boolean result = Boolean.parseBoolean(jsonObject.getString("result"));
            return result;
        }catch(JSONException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return false;
    }

    public Bitmap getImage(String image_name){
        String str_link = "http://"+image_link+"/"+image_name;
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute(str_link);
        return image;
    }

    public Boolean setImage(Bitmap image, String user_id){
        address = "http://"+ip+"/andy_set_image.php";
        new UploadImage(image, user_id).execute();
        try {
            JSONObject jsonObject = new JSONObject(output);
            Boolean result = Boolean.parseBoolean(jsonObject.getString("result"));
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

//    public Boolean setNewPassword(String password, Integer user_id){
//
//    }

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

    private class DownloadTask extends AsyncTask<String, Integer, Bitmap>{
        Bitmap bitmap = null;

        public Bitmap getBitmap() {
            return bitmap;
        }

        public void setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        @Override
        protected Bitmap doInBackground(String... url) {
            try{
                bitmap = download_image(url[0]);
            }catch(Exception e){
                Log.d("Background Task", e.toString());
            }
            set_image(bitmap);

            return bitmap;
        }
    }
    private Bitmap download_image(String strurl)throws IOException {
        Bitmap bitmap=null;
        InputStream iStream = null;
        try{
            URL url = new URL(strurl);
            /** Creating an http connection to communcate with url */
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            /** Connecting to url */
            urlConnection.connect();

            /** Reading data from url */
            iStream = urlConnection.getInputStream();

            /** Creating a bitmap from the stream returned from the url */
            bitmap = BitmapFactory.decodeStream(iStream);

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            iStream.close();
        }
        return bitmap;
    }
    private class UploadImage extends AsyncTask <Void, Void, Void>{
        Bitmap image;
        String name;
        public UploadImage(Bitmap image, String name){
            this.image = image;
            this.name = name;
        }
        @Override
        protected Void doInBackground(Void... params) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
            ArrayList<Pair<String, String>> data_to_send = new ArrayList<Pair<String, String>>();
            data_to_send.add(new Pair<String, String>("image", encodedImage));
            data_to_send.add(new Pair<String, String>("user_id", name));
            StringBuilder result = null;
            try {
                URL url = new URL(address);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                ArrayList<Pair<String, String>> to_send = data_to_send;
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getQuery(to_send));
                writer.flush();
                writer.close();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return_method(result.toString());
            return null;
        }
    }

    public void set_image(Bitmap bitmap){
        this.image = bitmap;
    }
}
