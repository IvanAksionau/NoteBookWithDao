package by.tc.nb.bean;

import by.tc.nb.bean.entity.Note;

import java.util.List;

public class FindNotesByContentResponse extends Response {
	private List<String> foundedNotes;

	public List<String> getFoundedNotes() {
		return foundedNotes;
	}

	public void setFoundedNotes(List<String> foundedNotes) {
		this.foundedNotes = foundedNotes;
	}

	
}
