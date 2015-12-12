package in.ac.iiitv.beyondbooks;

import java.util.ArrayList;

/**
 * Created by anjul on 13/11/15.
 */
public class ForumActivities {
    private ArrayList<ForumOverview> questions_started;
    private ArrayList<Comments> commented;

    ForumActivities(ArrayList<ForumOverview> _questions_started, ArrayList<Comments> _commented){
        this.questions_started = _questions_started;
        this.commented = _commented;
    }

    public ArrayList<ForumOverview> getQuestions_started() {
        return questions_started;
    }

    public void setQuestions_started(ArrayList<ForumOverview> questions_started) {
        this.questions_started = questions_started;
    }

    public ArrayList<Comments> getCommented() {
        return commented;
    }

    public void setCommented(ArrayList<Comments> commented) {
        this.commented = commented;
    }

}
