package model;

import java.util.List;

public class AppModel {
	
	private String title;
	
	@SuppressWarnings("rawtypes")
	List[] data = new List[3];
	

	@SuppressWarnings("rawtypes")
	public List[] getData() {
		return this.data;
	}
	
	@SuppressWarnings("rawtypes")
	public void setData(List[] data) {
		this.data = data;
	}
	
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

}
