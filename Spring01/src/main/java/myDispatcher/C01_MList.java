package myDispatcher;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
import vo.MemberVO;

public class C01_MList implements MyController {

	MemberService service = new MemberService();
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<MemberVO>list = service.selectList();
		request.setAttribute("Banana", list);
		
		
		return "member/memberList";
	}// handleRequest
}
