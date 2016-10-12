package by.tc.nb.bean;

/**
 * Created by Ivan on 04.10.2016.
 */
public class ShowNotesRequest extends Request {
    private int userID;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
