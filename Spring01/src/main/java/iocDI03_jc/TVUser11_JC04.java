package iocDI03_jc;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

// ** Java bean configuration class를 이용한 DI
// => test04 : xml 을 이용해서 JC 사용하기 
//    컨테이너 xml 용 사용
// => 스피커 2개 중 선택

public class TVUser11_JC04 {

	public static void main(String[] args) {
		// 1. 스프링 컨테이너 구동
		AbstractApplicationContext sc = new 
				GenericXmlApplicationContext("iocDI03_jc/app04.xml");
		
		// 2. 필요한 객체(TV) 를 전달 & 서비스 실행
		System.out.println("**  Test1) 고전적방법 : 직접 new  **");
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
