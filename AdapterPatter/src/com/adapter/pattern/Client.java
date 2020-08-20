package com.adapter.pattern;

public class Client {
	private Search titleSearch = new SearchImpl();
	private Search authorSearch = new SearchAdapter(new Find());
	
	public void searchKeyword(String keyword) {
		switch (keyword) {
		case "title":
			titleSearch.search(keyword);
			break;
		case "author":
			authorSearch.search(keyword);
			break;
		}
	}
}
