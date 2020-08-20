package com.templatemethod.pattern;

public class Client {
	public static void main(String[] args) {
		MakeClothes tShirt = new ShortSleevedTshirt();
		MakeClothes shoes = new RunningShoes();
		
		make(shoes);
	}
	
	public static void make(MakeClothes kind) {
		kind.start();
	}
}
