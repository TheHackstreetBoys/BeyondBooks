package in.ac.iiitv.beyondbooks;

import java.util.ArrayList;

/**
 * Created by anjul on 13/11/15.
 */
public class ForumDetails {
    private String title;
    private String author_name;
    private Integer author_id;
    private Integer id;
    private ArrayList<Comments> comments;
    private String details;
    private String tags;
    private String faculty_tags;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getFaculty_tags() {
        return faculty_tags;
    }

    public void setFaculty_tags(String faculty_tags) {
        this.faculty_tags = faculty_tags;
    }

    public ForumDetails(String title, String author_name, Integer id, Integer author_id, ArrayList<Comments> comments) {
        this.title = title;
        this.author_name = author_name;
        this.id = id;
        this.author_id = author_id;
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    public ArrayList<Comments> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comments> comments) {
        this.comments = comments;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
