package memberDI;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

// ** MemberList 출력
// => MemberService 를 주입 받아 처리
// => xml, @  활용

public class MemberUser {

	public static void main(String[] args) {
		// 1. 스프링컨테이너 정의
		AbstractApplicationContext sc = new
				GenericXmlApplicationContext("memberDI/member.xml");
		// 2. Service 인스턴스 전달받음
		MemberService service = (MemberService)sc.getBean("ss");
		
		// 3. Service 처리
		List<MemberVO> list = service.selectList();
		System.out.println("*** IOC/DI MemberList 출력 ***");
		for (MemberVO m:list) {
			System.out.println(m);
		} //for

	} //main

} //class
