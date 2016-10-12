package by.tc.nb.service;

import by.tc.nb.service.impl.NoteBookServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private NoteBookService nbService = new NoteBookServiceImpl();


    public static  ServiceFactory getInstance() {   ///synchronized ?
        return instance;
    }
/*private ServiceFactory(){
		NoteBookService = new NoteBookServiceImpl();//потому , что он и так final
	}*/

    public NoteBookService getNoteBookService()
    { ///какждый  вызов создаст new NoteBookServiceImpl ?
        return nbService;
    }

}
