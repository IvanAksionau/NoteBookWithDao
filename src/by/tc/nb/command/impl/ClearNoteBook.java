package by.tc.nb.command.impl;

import by.tc.nb.bean.ClearNoteBookRequest;
import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.service.NoteBookService;
import by.tc.nb.service.ServiceFactory;
import by.tc.nb.service.exception.ServiceException;


public class ClearNoteBook implements Command {

    @Override
    public Response execute(Request request) throws CommandException {
        ClearNoteBookRequest req = null;
        if (request instanceof ClearNoteBookRequest) {
            req = (ClearNoteBookRequest) request;
        } else {
            throw new CommandException("Wrong request");
        }
        int userID = req.getUserID();

        ServiceFactory service = ServiceFactory.getInstance();
        NoteBookService nbService = service.getNoteBookService();

        try {
            nbService.clearAllNotes(userID);
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage());
        }
        Response response = new Response();
        response.setErrorStatus(false);
        response.setResultMessage("Notebook cleared");
        return response;
    }

}
