package by.tc.nb.dao;

import by.tc.nb.dao.impl.mysql.MySQLNoteBookDAO;

/**
 * Created by Ivan on 09.10.2016.
 */
public class NoteBookDAOFactory {

    private static final NoteBookDAOFactory instance = new NoteBookDAOFactory();
    private NoteBookDAO noteBookDAO = new MySQLNoteBookDAO();

    public static NoteBookDAOFactory getInstance() {
        return instance;
    }

    public NoteBookDAO getNoteBookDAO() {
        return noteBookDAO;
    }

}
