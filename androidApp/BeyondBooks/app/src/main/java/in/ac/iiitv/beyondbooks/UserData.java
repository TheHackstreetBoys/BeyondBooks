package in.ac.iiitv.beyondbooks;

import java.util.ArrayList;

/**
 * Created by anjul on 11/11/15.
 */
public class UserData {
    private Integer id;
    ArrayList<Long> uploads;
    ArrayList<Long> reviewed;
    ArrayList<Long> enquired;

    public ArrayList<Long> getUploads() {
        return uploads;
    }

    public void setUploads(ArrayList<Long> uploads) {
        this.uploads = uploads;
    }

    public ArrayList<Long> getReviewed() {
        return reviewed;
    }

    public void setReviewed(ArrayList<Long> reviewed) {
        this.reviewed = reviewed;
    }

    public ArrayList<Long> getEnquired() {
        return enquired;
    }

    public void setEnquired(ArrayList<Long> enquired) {
        this.enquired = enquired;
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
