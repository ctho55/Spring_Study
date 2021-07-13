package iocDI02_anno;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;


/// ** Annotation, 애노테이션
//컴파일러에게 알려줌

//@WebServlet
//=> url 매핑네임을 알려줌 (매핑을 하지 않으면 404 오류발생)
//=> 복수선언 가능(배열형태로)
//단, 중복되면 절대 안됨!! :  Server Start가 안됨
//=> 한글은 가능 하나 비추
//=> web.xml (설정 파일) 에 설정할수도 있음.
//=> 두 방법은 각자의 장단점이 있다(용도에 맞게 사용)
//( @는 수정시 재컴파일 필요, web.xml 은 xml 문을 익혀야함 ..등등 )


//******************** @ Annotation 어노테이션 활용방법 *************************************

//1. xml에 context 네임스페이스 추가  [NameSpaces] 이용
//=> Component-scan 설정  : 
// <context:component-scan base-package="d0714.iocEx05" />
// 
//2. 소스코드 
//=> import 확인  : org.springframework.context.support.*;
//=> 생성을 원하는 TV 클래스 위에 @Component("tv") 설정


// => @Component("tv") 는
// 	 xml에서는 <bean ..> </bean>
//	javacode에서는 new 생성자() 와 동일하다 


//3.Test : 오류 메시지 확인 하기
//=> @ Test1 생성 -> <bean ... />
//=> @ Test2 id명 미 지정(둘다 beanname 없이) 
//=> ...NoSuchBeanDefinitionException: 
//  No bean named 'tv' is defined ....
//=> @ Test3 id명 중복되는 경우 (둘다 beanname  ("tv") 지정 )  
//=> ...annotation.ConflictingBeanDefinitionException: ....
//  ...non-compatible bean definition of same name and class ...



interface TV{
	public void poweron();
	public void poweroff();
	public void volumeUp();
	public void volumeDown();
}

//@Component("tv")
class SsTVi implements TV {
	public SsTVi(){System.out.println("~~SsTVi 생성자!~~");}
	public void poweron() {
		System.out.println("~~SsTVi power on~~");
	}
	public void poweroff() {
		System.out.println("~~SsTVi power off~~");
	}
	public void volumeUp() {
		System.out.println("~~SsTVi volumeUp~~");
	}
	public void volumeDown() {
		System.out.println("~~SsTVi volumeDown~~");
	}
}

//@Component("tv")
class LgTVi implements TV {
	public LgTVi(){System.out.println("~~LgTVi 생성자!~~");}
	public void poweron() {
		System.out.println("~~LgTVi power on~~");
	}
	public void poweroff() {
		System.out.println("~~LgTVi power off~~");
	}
	public void volumeUp() {
		System.out.println("~~LgTVi volumeUp~~");
	}
	public void volumeDown() {
		System.out.println("~~LgTVi volumeDown~~");
	}
}



public class TVUser05_Anno01 {

	public static void main(String[] args) {
		//1. 스프링 컨테이너 구동 
			AbstractApplicationContext sc = new GenericXmlApplicationContext("iocDI02_anno/app05.xml");	
				
		//2. 필요한 객체(TV)를 전달받음 & 서비스 실행 
		//2.1 고전적 방법
		System.out.println("** Test1) 고적적방법 : 직접 new **");
		TV tv = (TV)sc.getBean("tv");
		tv.poweron();
		tv.volumeUp();
		tv.volumeDown();
		tv.poweroff();
		
		
		sc.close();
	}//main
}//class
