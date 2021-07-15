package iocDI03_jc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

class Speaker {
	public Speaker() { System.out.println("~~ Speaker 생성자 ~~"); }
	public void volumeUp() { System.out.println(" ~~ Speaker volumeUp ~~"); }
	public void volumeDown() { System.out.println(" ~~ Speaker volumeDown ~~"); }
} //Speaker

//** Speaker에 대한 의존성(Dependency) 해결
//1. 고전적방법
class SsTVs implements TV {
	private Speaker sp = new Speaker();
	public SsTVs() {System.out.println("~~ SsTVs 생성자 ~~");}
	public void powerOn() { System.out.println(" ~~ SsTVs powerOn ~~"); }
	public void powerOff() { System.out.println(" ~~ SsTVs powerOff ~~"); }
	public void volumeUp() { sp.volumeUp(); }
	public void volumeDown() { sp.volumeDown(); }
} //SsTVs

//2. IOC/DI => 생성자 주입
class LgTVs implements TV {
	private Speaker sp;
	private int price;
	private String color;
	
	public LgTVs() {System.out.println(" ~~ LgTVs Default 생성자 ! ~~");} 
	// Speaker 객체 와 맴버 초기화를 위한 생성자 추가 
	public LgTVs(Speaker sp, int price, String color) {
		this.sp=sp; 
		this.price=price;
		System.out.println(" ~~ LgTVs 맴버 초기화 생성자 !!! price=> "+price+", "+color);}
	
	public void powerOn() { System.out.println(" ~~ LgTVs powerOn ~~"); }
	public void powerOff() { System.out.println(" ~~ LgTVs powerOff ~~"); }
	public void volumeUp() { sp.volumeUp(); }
	public void volumeDown() { sp.volumeDown(); }
} //LgTVs

// 3. IOC/DI => Setter 주입
class AiTVs implements TV {
	private Speaker sp;
	private int price;
	public AiTVs() {System.out.println(" ~~ AiTVs Default 생성자 ! ~~");} 
	
	// Setter 주입을 위한 Setter 추가 
	public void setSp(Speaker sp) {
		this.sp=sp;
		System.out.println(" ~~ Speaker Setter 주입 ! ~~");
		}
	public void setPrice(int price) {
		this.price=price;
		System.out.println(" ~~ price Setter 주입 ! => "+price);
		}
	public void powerOn() { System.out.println(" ~~ AiTVs powerOn ~~"); }
	public void powerOff() { System.out.println(" ~~ AiTVs powerOff ~~"); }
	public void volumeUp() { sp.volumeUp(); }
	public void volumeDown() { sp.volumeDown(); }
} //AiTVs

// ** Java bean configuration class를 이용한 DI
// => Bean 컨테이너 : AnnotationConfigApplicationContext 사용
// => test02 : 스피커 1개 사용 TV

public class TVUser09_JC02 {

	public static void main(String[] args) {
		// 1. 스프링 컨테이너 구동
		AbstractApplicationContext sc = new 
				AnnotationConfigApplicationContext(JavaConfig02.class);
		
		// 2. 필요한 객체(TV) 를 전달 & 서비스 실행
		System.out.println("**  Test1) 고전적방법 : 직접 new  **");
		TV tvs = (TV)sc.getBean("sstv");
		tvs.powerOn();
		tvs.volumeUp();
		tvs.volumeDown();
		tvs.powerOff();
		System.out.println("**  Test2) IOC/DI => 생성자 주입  **");
		TV tvl = (TV)sc.getBean("lgtv");
		tvl.powerOn();
		tvl.volumeUp();
		tvl.volumeDown();
		tvl.powerOff();
		System.out.println("**  Test3) IOC/DI => Setter 주입  **");
		TV tva = (TV)sc.getBean("aitv");
		tva.powerOn();
		tva.volumeUp();
		tva.volumeDown();
		tva.powerOff();
		// 3. 종료
		sc.close();
	} //main
} //class
