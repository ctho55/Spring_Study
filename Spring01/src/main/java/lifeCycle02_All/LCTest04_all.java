package lifeCycle02_All;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

//** Bean(객체) 의 LifeCycle 
//=> 객체생성 -> 사용 -> 소멸
//=> 해당되는 특정시점에 자동실행
//   xml, @, interface

//** Test4. ALL
//=> 실행순서: @ -> interface -> xml

class LifeCycleSample  implements InitializingBean, DisposableBean   {
	
	public LifeCycleSample() { System.out.println("~~ LifeCycleSample 생성자 ~~"); }
	// 1. xml 적용 메서드
	public void begin() { System.out.println("~~ LifeCycleSample begin() ~~"); } 
	public void end() { System.out.println("~~ LifeCycleSample end() ~~"); } 
	
	// 2. @ 적용 메서드
	@PostConstruct
	public void beginAnno() { System.out.println("~~ LifeCycleSample @PostConstruct ~~"); } 
	@PreDestroy
	public void endAnno() { System.out.println("~~ LifeCycleSample @PreDestroy ~~"); } 
	// 3. intreface
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("~~ LifeCycleSample afterPropertiesSet() ~~");
	}
	@Override
	public void destroy() throws Exception {
		System.out.println("~~ LifeCycleSample destroy() ~~");		
	}
	
	public void list() { System.out.println("~~ LifeCycleSample list() ~~"); } 
	public void login() { System.out.println("~~ LifeCycleSample login() ~~"); } 
} //LifeCycleSample

public class LCTest04_all {

	public static void main(String[] args) {
		AbstractApplicationContext sc = new
				GenericXmlApplicationContext("lifeCycle02_All/lc02.xml");
		LifeCycleSample lc = (LifeCycleSample)sc.getBean("lc");
		lc.login();
		lc.list();
		sc.close();
	} //main
} //class
