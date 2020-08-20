package com.pacade.pattern;

public class MainMachine {
	public void start() {
		SoakIn soakIn = new SoakIn();
		Sprinkle sprinkle = new Sprinkle();
		Mix mix = new Mix();
		
		soakIn.soakIn();
		sprinkle.sprinkle();
		mix.mix();
	}
}
