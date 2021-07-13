package iocDI02_anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

//** TV 는 Speaker 를 사용 (Speaker 를 의존)
//** 의존성 해결 
//** 생성자 주입 & setter 주입 : @(Annotation)방법


//** @Autowired
//=> 맴버변수에 직접 생성된 주소값을 전달
// 그러므로 Speaker 는 반드시 이미 생성되어 있어야 함.
//=> 자동주입: 있으면 주입 , 없으면 Exception 발생

//=> 적용순위
//1) 주로 멤버변수 위에 선언하며,
//  스프링 컨테이너는 해당변수의 타입을 체크하고,
//  그 타입의 객체가 메모리에 존재하는지 확인하여
//  해당 변수에 대입 해준다.

//2) 동일타입의 객체가 둘이상이면  @Qualifier 에 명시된 객체 주입
//3) 동일타입의 객체가 둘이상이고  @Qualifier 없으면 
// id가 같은 객체 찾아 있으면 주입, 없으면 오류

//** Test : Speaker 생성 안되어있는 경우 오류메시지 확인
//   => ...Injection of autowired dependencies failed;.....

//** required 속성
//=> true : 해당 타입의 등록된 빈이 없을때 주입시 익셉션이 발생
//=> false: 해당 타입의 등록된 빈이 없을때 익셉션이 발생하지 않음 
//      ( 있으면주입 , 없으면 null )    

//1) @Autowired(required=true) : default 
//   => BeanCreationException <- NoSuchBeanDefinitionException: .... as autowire...  
//2) @Autowired(required=false) 
//   => 실행문에서 접근시 NullPointerException 발생



//@Component
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

//@Component("sstv")
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

//@Component("Lgtv")
class LgTVs implements TV {
	
	//@Autowired(required=false) // 자동주입 
	// => 해당객체 (Speaker)는 반드시 이미 생성되어있어야함. 아래 구문의 = (대입) 역할
	//	private Speaker sp = new Speaker(); -> @component 역할
	// => 메모리에서 타입이 일치하는 객체를 찾아 있으면 제공 
	// => 주입 받으려는 객체위에 정의 
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

//@Component("aitv")
class AiTVs implements TV {
	//@Autowired(required=false) 
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




public class TVUser06_Anno02 {

	public static void main(String[] args) {
		//1. 스프링 컨테이너 구동 
		AbstractApplicationContext sc = new GenericXmlApplicationContext("iocDI02_anno/app05.xml");	
		
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
