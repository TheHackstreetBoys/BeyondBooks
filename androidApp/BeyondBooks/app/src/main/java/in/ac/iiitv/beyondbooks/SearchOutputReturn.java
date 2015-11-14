package in.ac.iiitv.beyondbooks;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by anjul on 11/11/15.
 */
public class SearchOutputReturn implements Serializable{
    private ArrayList<NewlyAdded> review;
    private ArrayList<NewlyAdded> buy_sell;
    private ArrayList<ForumOverview> forum;
    SearchOutputReturn(ArrayList<NewlyAdded> review, ArrayList<NewlyAdded> buy_sell, ArrayList<ForumOverview> forum){
        this.review = review;
        this.buy_sell = buy_sell;
        this.forum = forum;
    }

    public void setReview(ArrayList<NewlyAdded> review){
        this.review = review;
    }
    public  void setBuy_sell(ArrayList<NewlyAdded> buy_sell){
        this.buy_sell = buy_sell;
    }
    public void setForum(ArrayList<ForumOverview> forum){
        this.forum = forum;
    }
    public ArrayList<NewlyAdded> getReview(){
        return review;
    }
    public ArrayList<NewlyAdded> getBuy_sell(){
        return buy_sell;
    }
    public ArrayList<ForumOverview> getForum(){
        return forum;
    }
}
