package by.tc.nb.service.impl;

import by.tc.nb.dao.NoteBookDAOFactory;
import by.tc.nb.dao.UserDAOFactory;
import by.tc.nb.dao.exception.DAOException;
import by.tc.nb.service.NoteBookService;
import by.tc.nb.service.exception.ServiceException;

import java.util.ArrayList;


public class NoteBookServiceImpl implements NoteBookService {
    @Override
    public void addNote(String note, int userID) throws ServiceException {
        // parameters validation
        if (note == null || note.equals("")) {
            throw new ServiceException("Wrong parameter!");
        }
        try {
            NoteBookDAOFactory.getInstance()
                    .getNoteBookDAO()
                    .addNote(note, userID);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void clearAllNotes(int userID) throws ServiceException {
        // parameters validation
        if (userID <= 0) {
            throw new ServiceException("Invalid user ID");
        }
        try {
            NoteBookDAOFactory.getInstance()
                    .getNoteBookDAO()
                    .createNewNoteBook(userID);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ArrayList<String> findNotesByContent(String content, int userID) throws ServiceException {
        if (content == null || content.equals("") || userID <= 0) {
            throw new ServiceException("Wrong parameter!");
        }
        ArrayList result;
        try {
            result = NoteBookDAOFactory.getInstance()
                    .getNoteBookDAO()
                    .findNoteByContent(content, userID);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public ArrayList<String> findNotesByDate(String date, int userID) throws ServiceException {
        if (date == null || date.equals("") || !date.contains("-") || userID <= 0) {
            throw new ServiceException("Wrong parameter!");
        }
        ArrayList result;
        try {
            result = NoteBookDAOFactory.getInstance()
                    .getNoteBookDAO()
                    .findNotesByDate(date, userID);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public ArrayList<String> ShowNotes(int userID) throws ServiceException {
        if (userID <= 0) {
            throw new ServiceException("Invalid user ID");
        }
        ArrayList result;
        try {
            result = NoteBookDAOFactory.getInstance()
                    .getNoteBookDAO()
                    .showAllNotes(userID);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean registration(String login, String password) throws ServiceException {
        if (login == null || "".equals(login) || password == null || "".equals(password)) {
            throw new ServiceException("Wrong parameter!");
        }
        boolean result;
        try {
            result = UserDAOFactory.getInstance()
                    .getUserDAO()
                    .registration(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public int logging(String login, String password) throws ServiceException {
        if (login == null || login.equals("") || password == null || "".equals(password)) {
            throw new ServiceException("Wrong parameter!");
        }
        int result;
        try {
            result = UserDAOFactory
                    .getInstance()
                    .getUserDAO()
                    .logging(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }
}
