package by.tc.nb.dao;

import by.tc.nb.dao.exception.DAOException;

import java.util.ArrayList;

public interface NoteBookDAO {

	void addNote(String note, int userID) throws DAOException;

	void createNewNoteBook(int userID) throws DAOException;

	ArrayList findNoteByContent(String content, int userID) throws DAOException;

	ArrayList findNotesByDate(String date, int userID) throws DAOException;

	ArrayList showAllNotes(int userID) throws DAOException;
}
