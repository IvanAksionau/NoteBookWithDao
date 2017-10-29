package by.tc.nb.controller;

import by.tc.nb.command.Command;
import by.tc.nb.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandHelper {

	private Map<String, Command> commands = new HashMap<String, Command>();

	public CommandHelper() {
		commands.put("ADD_NEW_NOTE", new AddNewNote());
		commands.put("CLEAR_NOTE_BOOK", new ClearNoteBook());
		commands.put("FIND_NOTES_BY_CONTENT", new FindNotesByContent());
		commands.put("FIND_NOTE_BY_DATE", new FindNotesByDate());
		commands.put("SHOW_NOTES", new ShowNotes());
		commands.put("LOGIN_USER", new UserLogging());
		commands.put("REGISTERED_USER", new UserRegistration());
	}

	public Command getCommand(String commandName) {
		Command command;
		command = commands.get(commandName);
		return command;
	}
}
