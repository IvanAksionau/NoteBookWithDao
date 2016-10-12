package by.tc.nb.bean;

/**
 * Created by Ivan on 04.10.2016.
 */
public class FindNotesByContentRequest extends Request {
    private String content;
    private int userID;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
