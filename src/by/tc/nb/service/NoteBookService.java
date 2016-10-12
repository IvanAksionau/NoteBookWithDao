package by.tc.nb.service;

import by.tc.nb.service.exception.ServiceException;

import java.util.ArrayList;

public interface NoteBookService {
	
	void addNote(String note, int userID) throws ServiceException;
	void clearAllNotes(int userID) throws ServiceException;
	ArrayList<String> findNotesByContent(String content, int userID) throws ServiceException;
	ArrayList<String> findNotesByDate(String date, int userID) throws ServiceException;
	ArrayList<String> ShowNotes(int userID) throws ServiceException;
	int logging(String login, String password) throws ServiceException;
	boolean registration(String login, String password) throws ServiceException;
}
