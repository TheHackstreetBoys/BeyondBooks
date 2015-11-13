package in.ac.iiitv.beyondbooks;


/**
 * Created by anjul on 11/11/15.
 */
public class Comments {
    private Integer user_id; // Name of the user
    private String text;
    private Integer comment_id;

    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    Comments(Integer user_id, String text, Integer comment_id){
        this.user_id = user_id;
        this.text = text;
        this.comment_id = comment_id;

    }
    public String getText(){
        return text;
    }
    public void setText(String text){
        this.text = text;
    }
}
