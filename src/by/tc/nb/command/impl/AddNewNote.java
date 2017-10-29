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
        AddNewNoteRequest req;
        if (request instanceof AddNewNoteRequest) {
            req = (AddNewNoteRequest) request;
        } else {
            throw new CommandException("Wrong request");
        }

        NoteBookService nbService = ServiceFactory.getInstance().getNoteBookService();

        try {
            nbService.addNote(req.getNote(), req.getUserID());
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        Response response = new Response();
        response.setErrorStatus(false);
        response.setResultMessage("Note added !");
        return response;
    }
}
