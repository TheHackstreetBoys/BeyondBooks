package in.ac.iiitv.beyondbooks;

/**
 * Created by anjul on 11/11/15.
 */
public class NewlyAdded {
    private String image_link;
    private String book_name;
    private Float ratings;
    private Integer id;
    NewlyAdded(String image_link, String book_name, Float ratings, Integer id){
        this.image_link = image_link;
        this.book_name = book_name;
        this.ratings = ratings;
        this.id = id;
    }

    public void setBook_name(String book_name){
        this.book_name = book_name;
    }
    public void setImage_link(String image_link){
        this.image_link = image_link;
    }
    public String getBook_name(){
        return book_name;
    }
    public String getImage_link(){
        return image_link;
    }
    public void setRatings(Float ratings){
        this.ratings = ratings;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public Integer getId(){
        return id;
    }
    public Float getRatings(){
        return ratings;
    }
}
