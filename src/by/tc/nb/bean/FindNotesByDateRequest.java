package by.tc.nb.bean;

/**
 * Created by Ivan on 04.10.2016.
 */
public class FindNotesByDateRequest extends Request {
    private String date;
    private int userID;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
