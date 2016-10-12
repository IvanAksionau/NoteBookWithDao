package by.tc.nb.view;

import by.tc.nb.bean.*;
import by.tc.nb.controller.Controller;
import by.tc.nb.dao.impl.pool.ConnectionPool;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class View {
    private static final Controller controller = new Controller();
    private static final Scanner scanner = new Scanner(System.in);
    private static String help = "Enter : " + "\r\n"
            + "1 - Add new note into notebook" + "\r\n"
            + "2 - Clear the notebook" + "\r\n"
            + "3 - Find notes by content" + "\r\n"
            + "4 - Find notes by date" + "\r\n"
            + "5 - Show all notes";

    public static void main(String[] args) throws IOException {

        while (true) {
            System.out.println("Enter : " + "\n"
                    + "r - for registration" + "\r\n"
                    + "l - for logging" + "\r\n"
                    + "e - for exit");
            String appCommand = scanner.nextLine();
            if (appCommand.equals("e")) {
                ConnectionPool.getInstance().closePool();///CHECK IT!!!!!!!!!
                System.out.println("Program finished!");
                break;
            }
            switch (appCommand) {
                case "l":
                    UserLoggingRequest request1 = new UserLoggingRequest();
                    int currentUserID;
                    request1.setCommandName("LOGIN_USER");
                    System.out.println("Enter your login name");
                    String loginName = new Scanner(System.in).nextLine();
                    request1.setLogin(loginName);
                    System.out.println("Enter your password");
                    String pass = new Scanner(System.in).nextLine();
                    request1.setPassword(pass);
                    UserLoggingResponse response = (UserLoggingResponse) controller.doRequest(request1);
                    if (response.isErrorStatus() == false) {
                        currentUserID = response.getUserId();
                        System.out.println(response.getResultMessage());
                    } else {
                        System.out.println(response.getErrorMessage());
                        break;
                    }
                    while (true) {
                        System.out.println("Enter \"help\" in order to get the CommandMenu, or \"logout\" to logout");
                        String Command = scanner.nextLine();

                        if (Command.equals("logout")) {
                            ConnectionPool.getInstance().closePool();///CHECK IT!!!!!!!!!
                            System.out.println("System is logout!");
                            break;
                        }
                        switch (Command) {
                            case "help":
                                System.out.println(help);
                                break;
                            case "1"://AddNewNote
                                AddNewNoteRequest request2 = new AddNewNoteRequest();
                                request2.setCommandName("ADD_NEW_NOTE");
                                System.out.println("Enter new note :");
                                String note = new Scanner(System.in).nextLine();
                                request2.setNote(note);
                                request2.setUserID(currentUserID);
                                Response response2 = controller.doRequest(request2);
                                if (response2.isErrorStatus() == false) {
                                    System.out.println(response2.getResultMessage());
                                } else {
                                    System.out.println(response2.getErrorMessage());
                                }
                                break;
                            case "2"://ClearNoteBook
                                ClearNoteBookRequest request3 = new ClearNoteBookRequest();
                                request3.setCommandName("CLEAR_NOTE_BOOK");
                                request3.setUserID(currentUserID);
                                Response response3 = controller.doRequest(request3);
                                if (response3.isErrorStatus() == false) {
                                    System.out.println(response3.getResultMessage());
                                } else {
                                    System.out.println(response3.getErrorMessage());
                                }
                                break;

                            case "3"://FindNotesByContent
                                FindNotesByContentRequest request4 = new FindNotesByContentRequest();
                                request4.setCommandName("FIND_NOTES_BY_CONTENT");
                                System.out.println("Enter content for finding :");
                                request4.setContent(scanner.nextLine());
                                request4.setUserID(currentUserID);
                                FindNotesByContentResponse response4 = (FindNotesByContentResponse) controller.doRequest(request4);
                                if (response4.isErrorStatus() == false) {
                                    System.out.println(response4.getResultMessage());
                                    List<String> result = response4.getFoundedNotes();
                                    for (String contentNote : result) {
                                        System.out.println(contentNote);
                                    }
                                } else {
                                    System.out.println(response4.getErrorMessage());
                                }
                                break;
                            case "4"://FindNotesByDate
                                FindNotesByDateRequest request5 = new FindNotesByDateRequest();
                                request5.setCommandName("FIND_NOTE_BY_DATE");
                                System.out.println("Enter date of creation in format yyyy-mm-dd :");
                                request5.setDate(scanner.nextLine());
                                request5.setUserID(currentUserID);
                                FindNotesByDateResponse response5 = (FindNotesByDateResponse) controller.doRequest(request5);
                                if (response5.isErrorStatus() == false) {
                                    System.out.println(response5.getResultMessage());
                                    List<String> result = response5.getFoundedNotes();
                                    for (String contentNote : result) {
                                        System.out.println(contentNote);
                                    }
                                } else {
                                    System.out.println(response5.getErrorMessage());
                                }
                                break;

                            case "5"://ShowNotes
                                ShowNotesRequest request6 = new ShowNotesRequest();
                                request6.setCommandName("SHOW_NOTES");
                                request6.setUserID(currentUserID);
                                ShowNotesResponse response6 = (ShowNotesResponse) controller.doRequest(request6);
                                if (response6.isErrorStatus() == false) {
                                    System.out.println(response6.getResultMessage());
                                    List<String> result = response6.getFoundedNotes();
                                    for (String usersNotes : result) {
                                        System.out.println(usersNotes);
                                    }
                                } else {
                                    System.out.println(response6.getErrorMessage());
                                }
                                break;
                        }
                    }
                    break;
                case "r":
                    UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest();
                    userRegistrationRequest.setCommandName("REGISTERED_USER");
                    System.out.println("Enter your login name");
                    String newUserName = new Scanner(System.in).nextLine();
                    userRegistrationRequest.setLogin(newUserName);
                    System.out.println("Enter your password");
                    String newUserPass = new Scanner(System.in).nextLine();
                    userRegistrationRequest.setPassword(newUserPass);
                    UserRegistrationResponse userRegistrationResponse
                            = (UserRegistrationResponse) controller.doRequest(userRegistrationRequest);
                    if (userRegistrationResponse.isErrorStatus() == false) {
                        System.out.println(userRegistrationResponse.getResultMessage());
                    } else {
                        System.out.println(userRegistrationResponse.getErrorMessage());
                    }
                    break;
            }
        }
    }
}
