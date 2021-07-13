package iocDI02_anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

//** 생성자 주입 & setter 주입
//** TV 는 Speaker 를 사용 (Speaker 를 의존)


// => Speaker 가 여러개 
// => 명시적인 자동주입 
// @Autowired, @Qualifier("spA")

// => xml, @ 병행이 가능하다.
//  Speaker 의 생성은 xml에 <bean.. > 등록후 Test 



interface Speakeri{
	void volumeUp();
	void volumeDown();
}// Speaker i

//@Component("spA") 안쓰고 -> xml로 처리 
class SpeakerA implements Speakeri{
	public SpeakerA() { System.out.println("~~ SpeakerA 생성자 ~~");}
	public void volumeUp() {
		System.out.println("~~SpeakerA volumeUp~~");
	}
	public void volumeDown() {
		System.out.println("~~SpeakerA volumeDown~~");
	}
}// Speaker
class SpeakerB implements Speakeri{
	public SpeakerB() { System.out.println("~~ SpeakerB 생성자 ~~");}
	public void volumeUp() {
		System.out.println("~~SpeakerB volumeUp~~");
	}
	public void volumeDown() {
		System.out.println("~~SpeakerB volumeDown~~");
	}
}// Speaker


// ** Speaker 에대한 의존성(Dependency) 해결

// 1) 고전적방법 
// => (직접 new) : Speaker A B 을 교체시 소스를 수정해야한다. & 재컴파일 
@Component("sstvi")
class SsTVsi implements TV {
	private Speakeri sp = new SpeakerA();
	public SsTVsi() { System.out.println("~~ SsTVsi Default 생성자 ~~");} 
	
	public void poweron() {
		System.out.println("~~SsTVi power on~~");
	}
	public void poweroff() {
		System.out.println("~~SsTVi power off~~");
	}
	public void volumeUp() {sp.volumeUp();}
	public void volumeDown() {sp.volumeDown();}
}//SsTVs

//2) IOC/DI -> SpeakerA 또는 B 생성자 주입
@Component("Lgtvi")
class LgTVsi implements TV {
	@Autowired
	@Qualifier("spA")
	private Speakeri sp ;
	private int price ; 
	private String color ;
	
	public LgTVsi() { System.out.println("~~ LgTVsi Default 생성자 ~~");} 

	
	// Speaker 객체 초기화를 위한 생성자 추가 
	public LgTVsi(Speakeri sp, int price, String color ) { 
		this.sp=sp; this.price=price; this.color=color; 
		System.out.println("~~ LgTVsi 멤버 초기화 생성자 ! price => ~~"+price );
	} 
	
	public void poweron() {
		System.out.println("~~ LgTVsi power on~~");
	}
	public void poweroff() {
		System.out.println("~~ LgTVsi power off~~");
	}
	public void volumeUp() {sp.volumeUp();}
	public void volumeDown() {sp.volumeDown();}
}// Lgtvs


//3) IOC/DI -> setter 주입
@Component("aitvi")
class AiTVsi implements TV {
	
	@Autowired
	@Qualifier("spB")
	private Speakeri sp ;
	private int price; 
	
	public AiTVsi() { System.out.println("~~ AiTVs Default 생성자 ~~");} 
	
	//Setter 주입을 위한 Setter 추가 
	public void setSp (Speakeri sp) {
		this.sp=sp;
		System.out.println("~~ speakeri setter 주입 ! ~~");
	}
	
	public void setPrice (int price) {
		this.price=price;
		System.out.println("~~ price setter 주입 ! price =>"+price);
	}
	
	public void poweron() {
		System.out.println("~~AiTVsi power on~~");
	}
	public void poweroff() {
		System.out.println("~~AiTVsi power off~~");
	}
	public void volumeUp() {sp.volumeUp();}
	public void volumeDown() {sp.volumeDown();}
}




public class TVUser07_Anno03 {

	public static void main(String[] args) {
		//1. 스프링 컨테이너 구동 
		AbstractApplicationContext sc = new GenericXmlApplicationContext("iocDI02_anno/app05.xml");	
		
		//2. 필요한 객체(TV)를 전달받음 & 서비스 실행 
		//2.1 고전적 방법
		System.out.println("** Test1) 고적적방법 : 직접 new **");
		TV tvs = (TV)sc.getBean("sstvi");
		tvs.poweron();
		tvs.volumeUp();
		tvs.volumeDown();
		tvs.poweroff();
		
		System.out.println("** Test2) IOC/DI => 생성자 주입 **");
		TV tvl = (TV)sc.getBean("Lgtvi");
		tvl.poweron();
		tvl.volumeUp();
		tvl.volumeDown();
		tvl.poweroff();
		
		System.out.println("** Test3) Setter 주입 **");
		TV tva = (TV)sc.getBean("aitvi");
		tva.poweron();
		tva.volumeUp();
		tva.volumeDown();
		tva.poweroff();
		
		sc.close();
		
	}//main

}//class
