package com.templatemethod.pattern;

public class ShortSleevedTshirt extends MakeClothes {

	@Override
	protected void bringMeterial() {
		System.out.println("면을 가져온다.");
	}

	@Override
	protected void drawGuide() {
		System.out.println("반팔모양에 맞게 원단에 가이드를 그린다.");
		System.out.println("겉단과 안단을 자른다.");
	}

	@Override
	protected void operate() {
		System.out.println("접착심지로 겉단과 안단을 붙인다.");
		System.out.println("시침핀으로 고정한다.");
		System.out.println("미싱 기계를 돌려 완성한다.");
	}
}
