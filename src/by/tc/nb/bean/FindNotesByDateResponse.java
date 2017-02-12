package by.tc.nb.bean;


import java.util.List;

/**
 * Created by Ivan on 04.10.2016.
 */
public class FindNotesByDateResponse extends Response {
    private List<String> foundedNotes;

    public List<String> getFoundedNotes() {
        return foundedNotes;
    }

    public void setFoundedNotes(List<String> foundedNotes) {
        this.foundedNotes = foundedNotes;
    }
}
