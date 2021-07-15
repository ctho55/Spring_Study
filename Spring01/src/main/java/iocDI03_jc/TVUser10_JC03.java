package iocDI03_jc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

interface Speakeri {
	void volumeUp();
	void volumeDown();
} //Speakeri

class SpeakerA implements Speakeri {
	public SpeakerA() { System.out.println("~~ SpeakerAAA 생성자 ~~"); }
	public void volumeUp() { System.out.println(" ~~ SpeakerA volumeUp ~~"); }
	public void volumeDown() { System.out.println(" ~~ SpeakerA volumeDown ~~"); }
} //SpeakerA

class SpeakerB implements Speakeri {
	public SpeakerB() { System.out.println("~~ SpeakerBBB 생성자 ~~"); }
	public void volumeUp() { System.out.println(" ~~ SpeakerB volumeUp ~~"); }
	public void volumeDown() { System.out.println(" ~~ SpeakerB volumeDown ~~"); }
} //SpeakerA

// ** Speaker에 대한 의존성(Dependency) 해결
// 1. 고전적방법 
// => 직접 new : 스피커 A B 교체시 소스수정 & 재컴파일 
class SsTVsi implements TV {
	private Speakeri sp = new SpeakerA();
	public SsTVsi() {System.out.println("~~ SsTVsi 생성자 ~~");}
	public void powerOn() { System.out.println(" ~~ SsTVsi powerOn ~~"); }
	public void powerOff() { System.out.println(" ~~ SsTVsi powerOff ~~"); }
	public void volumeUp() { sp.volumeUp(); }
	public void volumeDown() { sp.volumeDown(); }
} //SsTVs

// 2. IOC/DI => SpeakderA 또는 B 를 생성자 주입
class LgTVsi implements TV {
	
	private Speakeri sp;
	private int price;
	private String color;
	
	public LgTVsi() {System.out.println(" ~~ LgTVsi Default 생성자 ! ~~");} 
	public LgTVsi(Speakeri sp, int price, String color) {
		this.sp=sp; 
		this.price=price;
		System.out.println(" ~~ LgTVsi 맴버 초기화 생성자 !!! price=> "+price+", "+color);}
	
	public void powerOn() { System.out.println(" ~~ LgTVsi powerOn ~~"); }
	public void powerOff() { System.out.println(" ~~ LgTVsi powerOff ~~"); }
	public void volumeUp() { sp.volumeUp(); }
	public void volumeDown() { sp.volumeDown(); }
} //LgTVsi

// 3. IOC/DI 
// => JC 와 @ Test
//    TV, SpeakerB 는 JC 에서 생성하고
//    @Autowired 로 주입 받음
class AiTVsi implements TV {
	
	// Speakeri sp  =   new SpeakerB();
	//              -   --------------
	//             @A..  JC 에서
	//  그러므로 @A..를 위해서는 반드시 이미 생성되어있어야함 
	@Autowired 
	@Qualifier("spA") // User10 은 spA , User11 은 spB 를 Test 함.
	private Speakeri sp;
	private int price;
	
	public AiTVsi() {System.out.println(" ~~ AiTVsi Default 생성자 ! ~~");} 
	public AiTVsi(Speakeri sp) {
		System.out.println(" ~~ AiTVsi Speakeri 주입 생성자 ! ~~");
		this.sp=sp; 
	} 
	public void setSp(Speakeri sp) {
		this.sp=sp;
		System.out.println(" ~~ Speakeri Setter 주입 ! ~~");
		}
	public void setPrice(int price) {
		this.price=price;
		System.out.println(" ~~ price Setter 주입 ! => "+price);
		}
	
	public void powerOn() { System.out.println(" ~~ AiTVsi powerOn ~~"); }
	public void powerOff() { System.out.println(" ~~ AiTVsi powerOff ~~"); }
	public void volumeUp() { sp.volumeUp(); }
	public void volumeDown() { sp.volumeDown(); }
} //AiTVsi

// ** Java bean configuration class를 이용한 DI
// => Bean 컨테이너 : AnnotationConfigApplicationContext 사용
// => test03 : 스피커 2개 중 선택

// ** JC 와 @ , xml 병행 처리 Test

public class TVUser10_JC03 {

	public static void main(String[] args) {
		// 1. 스프링 컨테이너 구동
		AbstractApplicationContext sc = new 
				AnnotationConfigApplicationContext(JavaConfig03.class);
		
		// 2. 필요한 객체(TV) 를 전달 & 서비스 실행
		System.out.println("**  Test1) 직접 new , xml 에서 생성 **");
		TV tvs = (TV)sc.getBean("sstv");
		tvs.powerOn();
		tvs.volumeUp();
		tvs.volumeDown();
		tvs.powerOff();
		System.out.println("**  Test2) JC => 생성자 주입  **");
		TV tvl = (TV)sc.getBean("lgtv");
		tvl.powerOn();
		tvl.volumeUp();
		tvl.volumeDown();
		tvl.powerOff();
		System.out.println("**  Test3) JC 와 @ 병행사용 **");
		TV tva = (TV)sc.getBean("aitv");
		tva.powerOn();
		tva.volumeUp();
		tva.volumeDown();
		tva.powerOff();
		// 3. 종료
		sc.close();
	} //main
} //class
