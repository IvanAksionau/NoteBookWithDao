package by.tc.nb.controller;

import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;

public class Controller {
	private CommandHelper helper = new CommandHelper();
	
	
	public Controller(){}
	
	public Response doRequest(Request request){
		String commandName = request.getCommandName();
		
		Command command = helper.getCommand(commandName);
		
		Response response;
		try {
			response = command.execute(request);
		} catch (CommandException e) {
			// logging
			response = new Response();
			response.setErrorStatus(true);
			response.setErrorMessage(e.getMessage());
		}
		return response;
		
	}

}
