package in.ac.iiitv.beyondbooks;


/**
 * Created by anjul on 11/11/15.
 */
public class Comments {
    private Integer user_id;
    private String user_name;
    private String text;
    private Integer q_id;
    private String q_title;
    private String comment_id;

    public Integer getQ_id() {
        return q_id;
    }

    public void setQ_id(Integer q_id) {
        this.q_id = q_id;
    }

    public String getQ_title() {
        return q_title;
    }

    public void setQ_title(String q_title) {
        this.q_title = q_title;
    }

    Comments(Integer user_id, String text, String comment_id, Integer q_id, String q_title){
        this.user_id = user_id;
        this.text = text;
        this.comment_id = comment_id;
        this.q_id = q_id;
        this.q_title = q_title;

    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getText(){
        return text;
    }
    public void setText(String text){
        this.text = text;
    }
}
