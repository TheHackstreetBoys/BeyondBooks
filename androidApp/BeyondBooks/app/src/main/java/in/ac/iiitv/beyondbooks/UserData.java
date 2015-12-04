package in.ac.iiitv.beyondbooks;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by anjul on 11/11/15.
 */
public class UserData implements Serializable{
    private Integer id;
    ArrayList<NewlyAdded> uploads;
    ArrayList<NewlyAdded> reviewed;
    ForumActivities forumActivities;
    private String user_name;
    private String image_link;

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public ForumActivities getForumActivities() {
        return forumActivities;
    }

    public void setForumActivities(ForumActivities forumActivities) {
        this.forumActivities = forumActivities;
    }

    public ArrayList<NewlyAdded> getUploads() {
        return uploads;
    }

    public void setUploads(ArrayList<NewlyAdded> uploads) {
        this.uploads = uploads;
    }

    public ArrayList<NewlyAdded> getReviewed() {
        return reviewed;
    }

    public void setReviewed(ArrayList<NewlyAdded> reviewed) {
        this.reviewed = reviewed;
    }

    UserData(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
