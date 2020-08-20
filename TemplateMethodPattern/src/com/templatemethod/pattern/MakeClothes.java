package com.templatemethod.pattern;

public abstract class MakeClothes {
	protected abstract void bringMeterial();
	protected abstract void drawGuide();
	protected abstract void operate();
	
	public void start() {
		bringMeterial();
		drawGuide();
		operate();
	}
}
