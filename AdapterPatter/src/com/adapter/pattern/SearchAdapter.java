package com.adapter.pattern;

public class SearchAdapter implements Search {
	
	private Find find;
	
	public SearchAdapter(Find find) {
		this.find = find;
	}
	
	@Override
	public void search(String keyword) {
		find.find(keyword, true);
	}
}
