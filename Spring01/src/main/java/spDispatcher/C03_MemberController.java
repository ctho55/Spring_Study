package spDispatcher;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.MemberService;
import vo.MemberVO;



//** 스프링MVC : 스프링 DispatcherServlet 사용 
//=> Ano 기반  

//** @Component (bean 생성 @) 의 세분화 
//=> 스프링 프레임워크에서는 클래스들을 기능별로 분류하기 위해 @ 을 추가함.
//=>  @Controller:사용자요청을 제어하는 Controller 클래스
//             DispatcherServlet이 해당 객체를 Controller객체로 인식하게해줌.    
//=>  @Service : 비즈니스로직을 담당하는 Service 클래스
//=>  @Repository:DB 연동을 담당하는 DAO 클래스
//          DB 연동과정에서 발생하는 예외를 변환 해주는 기능 추가 

//*** @Controller 사용함
//=> implements Controller 를 대신함.
//=> 아래와 관련된 import 삭제 해야 함.
//public class LoginController implements Controller {
//=> 메서드명, 매개변수, 리턴값 에 자유로워짐 
// -> 메서드명은 handleRequest 사용안해도 됨
// -> 매개변수 다양한 정의 가능 (메서드내에서 생성할 필요 없어짐)
// -> 리턴값은 ModelAndView 또는 String 가능함

//=> 요청별 Controller 를 한 클래스내에 메서드로 구현할 수 있게 됨  
//=> 요청 과 메서드 연결 은 @RequestMapping 으로
// Controller 클래스 코드내에서 직접 매핑 
// => xml 에 설정한 SimpleUrlHandlerMapping 부분 필요가 없음 

// 그러므로 대부분 Table 별로 Controller 클래스를 작성함 .

//------------------------------------------------------

@org.springframework.stereotype.Controller
public class C03_MemberController{
	
	
	@Autowired
	MemberService service;
	
	@RequestMapping(value="/mlista")
	public ModelAndView meberList(ModelAndView mv) {
		
		//ModelAndView mv= new ModelAndView(); 
		//객체를 매핑메서드의 매개변수로 정의하면 따로 생성하지 않아도 됨 

		List<MemberVO>list = service.selectList();
		mv.addObject("Banana", list);
		mv.setViewName("member/memberList");
		
		return mv;
	}// handleRequest

		@RequestMapping(value="/joinf")
		public ModelAndView joinf(ModelAndView mv) {

			mv.setViewName("member/joinForm");
			return mv;
		}//joinf	
		
		@RequestMapping(value="/mjoin")
		public ModelAndView mjoin(ModelAndView mv, MemberVO vo) {
			
			if (service.insert(vo) > 0) {
				// 가입성공 => message, 로그인 (loginForm.jsp)
				mv.addObject("message","~~ 환영 합니다 !! 로그인 후 이용하세요 ~~")  ;
				mv.setViewName("member/loginForm");
			}
			
			
			return mv;
		}//join
	
	
}//C03_MemberController
