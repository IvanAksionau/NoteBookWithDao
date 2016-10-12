package by.tc.nb.command.impl;

import by.tc.nb.bean.AddNewNoteRequest;
import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.service.NoteBookService;
import by.tc.nb.service.ServiceFactory;
import by.tc.nb.service.exception.ServiceException;

public class AddNewNote implements Command {

    @Override
    public Response execute(Request request) throws CommandException {
        AddNewNoteRequest req = null;
        if (request instanceof AddNewNoteRequest) {
            req = (AddNewNoteRequest) request;
        } else {
            throw new CommandException("Wrong request");
        }

        String note = req.getNote();
        int userID = req.getUserID();
        ServiceFactory service = ServiceFactory.getInstance();
        NoteBookService nbService = service.getNoteBookService();

        try {
            nbService.addNote(note,userID);
        } catch (ServiceException e) {
            throw new CommandException();
        }

        Response response = new Response();
        response.setErrorStatus(false);
        response.setResultMessage("Note added !");
        return response;
    }
}
