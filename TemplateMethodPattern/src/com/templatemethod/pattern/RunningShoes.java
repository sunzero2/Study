package com.templatemethod.pattern;

public class RunningShoes extends MakeClothes {

	@Override
	protected void bringMeterial() {
		System.out.println("네오프린과 신발끈, 가죽을 가져온다.");
	}

	@Override
	protected void drawGuide() {
		System.out.println("패턴을 종이에 그린다.");
		System.out.println("원단을 패턴에 맞게 자른다.");
	}

	@Override
	protected void operate() {
		System.out.println("라스트(틀)에 원단을 감싼다.");
		System.out.println("핀으로 임시 고정한다.");
		System.out.println("기타 등등 작업을 한다.");
	}
}
