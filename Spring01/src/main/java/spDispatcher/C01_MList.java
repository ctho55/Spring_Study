package spDispatcher;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import service.MemberService;
import vo.MemberVO;

public class C01_MList implements Controller {
	
	// MemberService service = new MemberService() ;
	// => IOC/Di 적용
	// => 전역변수 : @AutoWired
	// MemberService
	
	@Autowired
	MemberService service;
	
	// return 타입이 String이 아닌 spring에서 제공하는 타입 ModelAndView
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		ModelAndView mv = new ModelAndView();
		
		List<MemberVO>list = service.selectList();
		
		mv.addObject("Banana", list);
		// 같은 동작 
		//request.setAttribute("Banana", list);
		mv.setViewName("member/memberList");
		
		return mv;
	}// handleRequest
}
