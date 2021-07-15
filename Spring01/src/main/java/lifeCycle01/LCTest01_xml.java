package lifeCycle01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

//** Bean(객체) 의 LifeCycle 
//=> 객체생성 -> 사용 -> 소멸
//=> 해당되는 특정시점에 자동실행
//   xml, @, interface

//** Test1. xml
//=> xml 에 해당 시점별 속성에 등록
//   init-method="begin" destroy-method="end" 
//=> 실행 순서
//   생성자 -> init-method(자동호출) -> 사용...  
//        -> 컨테이너 Close -> destroy-method(자동호출)

class LifeCycleSample {
	public LifeCycleSample() { System.out.println("~~ LifeCycleSample 생성자 ~~"); }
	public void begin() { System.out.println("~~ LifeCycleSample begin() ~~"); } 
	public void end() { System.out.println("~~ LifeCycleSample end() ~~"); } 
	public void list() { System.out.println("~~ LifeCycleSample list() ~~"); } 
	public void login() { System.out.println("~~ LifeCycleSample login() ~~"); } 
} //LifeCycleSample

public class LCTest01_xml {

	public static void main(String[] args) {
		AbstractApplicationContext sc = new
				GenericXmlApplicationContext("lifeCycle01/lc01.xml");
		LifeCycleSample lc = (LifeCycleSample)sc.getBean("lc") ;
		lc.login();
		lc.list();
		sc.close();
	} //main

} //class
