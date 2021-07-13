package iocDI01_xml;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

//** 스프링이 제공하는 BeanFactory
//=> 스프링 컨테이너
//=> 컨테이너는 xml (설정화일), @, JavaCode (JavaConfig) 등의 
// 전달사항을 통해 요구하는 클래스를 생성, 보관, 제공

// ** Xml (설정화일)
public class TVUser02 {

	public static void main(String[] args) {
		//** 1. 콩공장(BeanFactory) 생성 -> 스프링 컨테이너 구동 
		AbstractApplicationContext sc = new GenericXmlApplicationContext("iocDI01_xml/app02.xml");
		
		//** 2. 필요한 객체를 전달받음 
		// => 필요한 객체를 설정화일을 이용해서 Spring 컨테이너 에게 요청 
		// => Spring 컨테이너는 getBean 메서드를 실행해서 해당객체를 제공 
		// => 실시간으로 소스코드 수정없이 전달받음 
		
		
		
		
//		TV tvf = bf.getBean(args[0]); // 실시간으로 소스코드 수정없이 요청전달
		TV tv = (TV)sc.getBean("tv");
		if (tv !=null) {
			
			// ** 3. 서비스실행 
			
			tv.poweron();
			tv.volumeUp();
			tv.volumeDown();
			tv.poweroff();
		}else System.out.println("TV 선택 오류 ~");
		
		 // ** 4. SingleTon Test
	      // => 스프링 프레임 웤의 모든 작업은 싱글톤을 기본으로함.
	      // => 싱글톤 (한개의 인스턴스만 허용 하는것) 적용 Test
	      // => 설정화일의 scope 속성 에 "prototype" / "singleton" (default 는 싱글톤)
	      // => 생성자 실행횟수와 아래의 주소값  확인해보기
		TV tv2 = (TV)sc.getBean("tv"); // SsTVi
		TV tvlg =(TV)sc.getBean("lgtv"); 
		TV tvlg2 =(TV)sc.getBean("lgtv"); 
		
		System.out.println("** SingleTon Test **");
		System.out.println("** tv **"+tv);
		System.out.println("** tv2 **"+tv2);
		System.out.println("** tvlg **"+tvlg);
		System.out.println("** tvlg2 **"+tvlg2);
		
		
		sc.close();
		
	}//main
}// class
