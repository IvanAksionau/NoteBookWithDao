package by.tc.nb.command.impl;

import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.bean.UserRegistrationRequest;
import by.tc.nb.bean.UserRegistrationResponse;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.service.NoteBookService;
import by.tc.nb.service.ServiceFactory;
import by.tc.nb.service.exception.ServiceException;

/**
 * Created by Ivan on 12.10.2016.
 */
public class UserRegistration implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        UserRegistrationRequest req = null;
        if (request instanceof UserRegistrationRequest) {
            req = (UserRegistrationRequest) request;
        } else {
            throw new CommandException("Wrong request");
        }

        String login = req.getLogin();
        String password = req.getPassword();
        boolean resultId;
        ServiceFactory service = ServiceFactory.getInstance();
        NoteBookService nbService = service.getNoteBookService();

        try {
            resultId = nbService.registration(login,password);
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage());
        }

        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setRegistrationStatus(resultId);
        response.setErrorStatus(false);
        if (resultId == true) {
            response.setResultMessage("Hello " + login + " !" + "You was registered !");
        }
        else response.setResultMessage("login \"" + login + "\" already exist!");
        return response;
    }
}
