package kr.or.iei.vo;

public class BeanFactory {
	
	public TV getBean(String brand) {
		if(brand.equals("samsung")) {
			SamsungTV s = new SamsungTV();
			return s;
		}else if(brand.equals("lg")) {
			LgTV l = new LgTV();
			return l;
		}
		return null;
	}
}
