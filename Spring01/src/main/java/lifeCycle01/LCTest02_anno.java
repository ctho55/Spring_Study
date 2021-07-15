package lifeCycle01;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

//** Bean(객체) 의 LifeCycle 
//=> 객체생성 -> 사용 -> 소멸
//=> 해당되는 특정시점에 자동실행
// xml, @, interface

//** Test2. @ Annotation
//=> @PostConstruct , @PreDestroy
//=> 실행 순서
//   생성자 -> @PostConstruct (자동호출) -> 사용...  
//        -> 컨테이너 Close -> @PreDestroy (자동호출)

@Component("lca")
class LifeCycleSampleA {
	public LifeCycleSampleA() { System.out.println("~~ LifeCycleSampleA 생성자 ~~"); }
	
	@PostConstruct
	public void begin() { System.out.println("~~ LifeCycleSample @PostConstruct ~~"); } 
	@PreDestroy
	public void end() { System.out.println("~~ LifeCycleSample @PreDestroy ~~"); } 
	
	public void list() { System.out.println("~~ LifeCycleSample list() ~~"); } 
	public void login() { System.out.println("~~ LifeCycleSample login() ~~"); } 
} //LifeCycleSample

public class LCTest02_anno {

	public static void main(String[] args) {
		AbstractApplicationContext sc = new
				GenericXmlApplicationContext("lifeCycle01/lc01.xml");
		LifeCycleSampleA lca = (LifeCycleSampleA)sc.getBean("lca");
		lca.login();
		lca.list();
		sc.close();
	} //main
} //class
