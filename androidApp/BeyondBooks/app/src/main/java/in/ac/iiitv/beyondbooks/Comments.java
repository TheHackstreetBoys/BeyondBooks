package in.ac.iiitv.beyondbooks;

/**
 * Created by anjul on 11/11/15.
 */
public class Comments {
    private String name; // Name of the user
    private String text;
    Comments(String name, String text){
        this.name = name;
        this.text = text;
    }
    public String getName(){
        return name;
    }
    public String getText(){
        return text;
    }
    public void  setName(String name){
        this.name = name;
    }
    public void setText(String text){
        this.text = text;
    }
}
