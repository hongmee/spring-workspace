package kr.or.iei.vo;

public class SamsungTV implements TV {
	public void powerOn() {
		System.out.println("SamsungTV ----- 전원을 켠다.");
	}
	public void powerOff() {
		System.out.println("SamsungTV ----- 전원을 끈다.");
	}
	public void volumeUp() {
		System.out.println("SamsungTV ----- 소리를 올림.");
	}
	public void volumeDown() {
		System.out.println("SamsungTV ----- 소리를 내림.");
	}
}
