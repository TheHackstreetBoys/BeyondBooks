package in.ac.iiitv.beyondbooks;

/**
 * Created by anjul on 11/11/15.
 */
public class ForumOverview {
    private String title;
    private String author;
    private Integer author_id;
    ForumOverview(String title, String author, Integer author_id){
        this.title = title;
        this.author = author;
        this.author_id = author_id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public void setAuthor_id(Integer author_id){
        this.author_id = author_id;
    }
    public Integer getAuthor_id(){
        return author_id;
    }
}
