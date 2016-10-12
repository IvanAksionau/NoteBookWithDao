package by.tc.nb.command;

import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.command.exception.CommandException;

public interface Command {
	Response execute(Request request) throws CommandException;
}
