package by.tc.nb.command.impl;

import by.tc.nb.bean.FindNotesByDateRequest;
import by.tc.nb.bean.FindNotesByDateResponse;
import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.bean.entity.Note;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.service.NoteBookService;
import by.tc.nb.service.ServiceFactory;
import by.tc.nb.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Ivan on 29.09.2016.
 */
public class FindNotesByDate implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        FindNotesByDateRequest req = null;
        if (request instanceof FindNotesByDateRequest) {
            req = (FindNotesByDateRequest) request;
        } else {
            throw new CommandException("Wrong request");
        }

        String date = req.getDate();
        int userID = req.getUserID();
        List<String> result = null;
        ServiceFactory service = ServiceFactory.getInstance();
        NoteBookService nbService = service.getNoteBookService();
        try {
            result = nbService.findNotesByDate(date, userID);
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage());
        }

        FindNotesByDateResponse response = new FindNotesByDateResponse();
        response.setFoundedNotes(result);
        response.setErrorStatus(false);
        response.setResultMessage("Count of founded notes is " + result.size());
        return response;
    }
}
