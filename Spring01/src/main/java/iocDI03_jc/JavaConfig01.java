package iocDI03_jc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

//** @Configuration 
// => 해당 클래스가 스프링의 설정파일로 사용됨을 지정
//	  이 클래스는 final이  아니어야 하며
//	  파라미터 없는 기본 생성자를 반드시 제공 해야 함

// => xml 과 혼합해서 사용하는 경우 자바 설정 임포트
// => @ImportResource 적용 ( JavaConfig01.java )
// => @ImportResource("iocDI05_Jc/applicationContext.xml")

// => Xml 에서  자바 설정 화일을 import 적용하기
// => JavaConfig05, TvUserJavaC05, applicationContext05.xml

//** @Bean 과 메서드 이름으로 스프링 컨테이너가 사용할 빈 객체를 설정
// => 메서드 이름이 BeanName 으로 사용됨
// => User Class 에서 getBean("BeanName") 에 사용됨
//** 비교
//  <bean id="tree" class="iocDI03_jc.SsTvi" />
//  new 연산자로 직접 생성함.
//  싱글톤을 기본으로 함

//** Java bean configuration class를 이용한 DI
//** test01 : 스피커 없는 TV

@Configuration
public class JavaConfig01 {
	@Bean
	public TV tree() {
		return new LgTVi();
	} //tree

} //class
