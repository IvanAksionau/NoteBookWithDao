package by.tc.nb.command.impl;

import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.bean.UserLoggingRequest;
import by.tc.nb.bean.UserLoggingResponse;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.service.NoteBookService;
import by.tc.nb.service.ServiceFactory;
import by.tc.nb.service.exception.ServiceException;

/**
 * Created by Ivan on 12.10.2016.
 */
public class UserLogging implements Command {

    @Override
    public Response execute(Request request) throws CommandException {
        UserLoggingRequest req;
        if (request instanceof UserLoggingRequest) {
            req = (UserLoggingRequest) request;
        } else {
            throw new CommandException("Wrong request");
        }

        String login = req.getLogin();
        int resultId;
        ServiceFactory service = ServiceFactory.getInstance();
        NoteBookService nbService = service.getNoteBookService();

        try {
            resultId = nbService.logging(login, req.getPassword());
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage());
        }

        UserLoggingResponse response = new UserLoggingResponse();
        response.setUserId(resultId);
        response.setErrorStatus(false);
        response.setResultMessage("Hello " + login + " !");
        return response;
    }
}
