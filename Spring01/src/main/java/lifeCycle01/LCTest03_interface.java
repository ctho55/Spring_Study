package lifeCycle01;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

//** Bean(객체) 의 LifeCycle 
//=> 객체생성 -> 사용 -> 소멸
//=> 해당되는 특정시점에 자동실행
//   xml, @, interface

//** Test3. interface
//=> 각 시점별로 자동호출되는 메서드를 구현해 놓은 interface 를 활용
//   -> InitializingBean : afterPropertiesSet()
//   -> DisposableBean : destroy()
//=> 실행 순서
//   생성자 -> afterPropertiesSet() (자동호출) -> 사용...  
//        -> 컨테이너 Close -> destroy() (자동호출)

@Component("lci")
class LifeCycleSampleI  implements InitializingBean, DisposableBean   {
	
	public LifeCycleSampleI() { System.out.println("~~ LifeCycleSampleI 생성자 ~~"); }
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("~~ LifeCycleSampleI afterPropertiesSet() ~~");
	}
	@Override
	public void destroy() throws Exception {
		System.out.println("~~ LifeCycleSampleI destroy() ~~");		
	}
	public void list() { System.out.println("~~ LifeCycleSample list() ~~"); } 
	public void login() { System.out.println("~~ LifeCycleSample login() ~~"); } 
} //LifeCycleSample

public class LCTest03_interface {

	public static void main(String[] args) {
		AbstractApplicationContext sc = new
				GenericXmlApplicationContext("lifeCycle01/lc01.xml");
		LifeCycleSampleI lci = (LifeCycleSampleI)sc.getBean("lci");
		lci.login();
		lci.list();
		sc.close();
	} //main
} //class
