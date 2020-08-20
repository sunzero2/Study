package com.singleton.pattern;

public class NonKeywordSingleton {
	private NonKeywordSingleton() {}
	
	private static class SingletonHolder {
		public static final NonKeywordSingleton INSTANCE = new NonKeywordSingleton();
	}
	
	public static NonKeywordSingleton getInstance() {
		return SingletonHolder.INSTANCE;
	}
}
