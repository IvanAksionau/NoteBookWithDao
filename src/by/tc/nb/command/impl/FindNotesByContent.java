package by.tc.nb.command.impl;

import by.tc.nb.bean.FindNotesByContentRequest;
import by.tc.nb.bean.FindNotesByContentResponse;
import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.service.NoteBookService;
import by.tc.nb.service.ServiceFactory;
import by.tc.nb.service.exception.ServiceException;

import java.util.List;

public class FindNotesByContent implements Command {

    @Override
    public Response execute(Request request) throws CommandException {
        FindNotesByContentRequest req = null;
        if (request instanceof FindNotesByContentRequest) {
            req = (FindNotesByContentRequest) request;
        } else {
            throw new CommandException("Wrong request");
        }

        String content = req.getContent();
        int userID = req.getUserID();
        ServiceFactory service = ServiceFactory.getInstance();
        NoteBookService nbService = service.getNoteBookService();

        List<String> result = null;
        try {
            result = nbService.findNotesByContent(content, userID);
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage());
        }

        FindNotesByContentResponse response = new FindNotesByContentResponse();
        response.setFoundedNotes(result);
        response.setErrorStatus(false);
        response.setResultMessage("Count of founded notes is " + result.size());
        return response;
    }

}
