package in.ac.iiitv.beyondbooks;

import java.util.ArrayList;

/**
 * Created by anjul on 11/11/15.
 */
public class UserData {
    private Integer id;
    ArrayList<NewlyAdded> uploads;
    ArrayList<NewlyAdded> reviewed;
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
