package iocDI01_xml;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

//** 생성자 주입 & setter 주입
//** TV 는 Speaker 를 사용 (Speaker 를 의존)
//** 의존성 해결 

// Test 
//1) 고전적 방법 (직접 new)
//2) IOC/DI -> 생성자 주입 
//3) IOC/DI -> setter 주입

//** IOC: 제어의 역전 (외부에서 Control)
//** DI : 주입 받음으로 해결


class Speaker{
	public Speaker() { System.out.println("~~ Speaker 생성자 ~~");}
	public void volumeUp() {
		System.out.println("~~Speaker volumeUp~~");
	}
	public void volumeDown() {
		System.out.println("~~Speaker volumeDown~~");
	}
}// Speaker


// ** Speaker 에대한 의존성(Dependency) 해결

// 1) 고전적방법 
// => (직접 new) : Speaker을 교체시 소스를 수정해야한다. & 재컴파일 

class SsTVs implements TV {
	private Speaker sp = new Speaker();
	public SsTVs() { System.out.println("~~ SsTVs 생성자 ~~");} 
	
	public void poweron() {
		System.out.println("~~SsTVi power on~~");
	}
	public void poweroff() {
		System.out.println("~~SsTVi power off~~");
	}
	public void volumeUp() {sp.volumeUp();}
	public void volumeDown() {sp.volumeDown();}
}//SsTVs

//2) IOC/DI -> 생성자 주입
class LgTVs implements TV {
	private Speaker sp ;
	private int price ; 
	private String color ;
	
	public LgTVs() { System.out.println("~~ LgTVs 생성자 ~~");} 

	
	// Speaker 객체 초기화를 위한 생성자 추가 
	public LgTVs(Speaker sp, int price, String color ) { 
		this.sp=sp; this.price=price; this.color=color; 
		System.out.println("~~ LgTVs 멤버 초기화 생성자 ! price => ~~"+price );
	} 
	
	public void poweron() {
		System.out.println("~~ LgTVs power on~~");
	}
	public void poweroff() {
		System.out.println("~~ LgTVs power off~~");
	}
	public void volumeUp() {sp.volumeUp();}
	public void volumeDown() {sp.volumeDown();}
}// Lgtvs


//3) IOC/DI -> setter 주입
class AiTVs implements TV {
	private Speaker sp ;
	private int price; 
	
	public AiTVs() { System.out.println("~~ AiTVs 생성자 ~~");} 
	
	//Setter 주입을 위한 Setter 추가 
	public void setSp (Speaker sp) {
		this.sp=sp;
		System.out.println("~~ setter 주입 ! ~~");
	}
	
	public void setPrice (int price) {
		this.price=price;
		System.out.println("~~ price setter 주입 ! price =>"+price);
	}
	
	public void poweron() {
		System.out.println("~~AiTVs power on~~");
	}
	public void poweroff() {
		System.out.println("~~AiTVs power off~~");
	}
	public void volumeUp() {sp.volumeUp();}
	public void volumeDown() {sp.volumeDown();}
}




public class TVUser03_Speaker {

	public static void main(String[] args) {
		//1. 스프링 컨테이너 구동 
		AbstractApplicationContext sc = new GenericXmlApplicationContext("iocDI01_xml/app03.xml");	
		
		//2. 필요한 객체(TV)를 전달받음 & 서비스 실행 
		//2.1 고전적 방법
		System.out.println("** Test1) 고적적방법 : 직접 new **");
		TV tvs = (TV)sc.getBean("sstv");
		tvs.poweron();
		tvs.volumeUp();
		tvs.volumeDown();
		tvs.poweroff();
		
		System.out.println("** Test2) IOC/DI => 생성자 주입 **");
		TV tvl = (TV)sc.getBean("Lgtv");
		tvl.poweron();
		tvl.volumeUp();
		tvl.volumeDown();
		tvl.poweroff();
		
		System.out.println("** Test3) Setter 주입 **");
		TV tva = (TV)sc.getBean("aitv");
		tva.poweron();
		tva.volumeUp();
		tva.volumeDown();
		tva.poweroff();
		
		sc.close();
		
	}//main

}//class
