package by.tc.nb.command.impl;

import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.bean.ShowNotesRequest;
import by.tc.nb.bean.ShowNotesResponse;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.service.NoteBookService;
import by.tc.nb.service.ServiceFactory;
import by.tc.nb.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Ivan on 29.09.2016.
 */
public class ShowNotes implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        ShowNotesRequest req = null;
        if (request instanceof ShowNotesRequest) {
            req = (ShowNotesRequest) request;
        } else {
            throw new CommandException("Wrong request");
        }

        int userID = req.getUserID();
        List<String> result = null;
        ServiceFactory service = ServiceFactory.getInstance();
        NoteBookService nbService = service.getNoteBookService();

        try {
            result = nbService.ShowNotes(userID);
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage());
        }

        ShowNotesResponse response = new ShowNotesResponse();
        response.setFoundedNotes(result);
        response.setErrorStatus(false);
        response.setResultMessage("count of founded notes is " + result.size());
        return response;
    }
}
