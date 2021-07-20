package com.ncs.green;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.MemberService;
import vo.MemberVO;

@Controller
public class MemberController {
	@Autowired
	MemberService service;

	@RequestMapping(value = "/mlist")
	public ModelAndView mlist(ModelAndView mv) {

		List<MemberVO> list = service.selectList();

		if (list != null) {
			mv.addObject("Banana", list);
		} else {
			mv.addObject("message", "!! 출력할 자료가 한건도 없습니다. !!");
		}

		mv.setViewName("member/memberList");
		return mv;
	}// mlist

	// Loginf
	@RequestMapping(value = "/loginf")
	public ModelAndView login(ModelAndView mv) {
		mv.setViewName("member/loginForm");
		return mv;
	}

	// login
	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, ModelAndView mv, MemberVO vo) {
		
		String password= vo.getPassword();
		// => 입력값의 오류에 대한 확인은 UI 에서 JavaScript로 처리 
		vo = service.selectOne(vo);
		if(vo != null) {
			if(vo.getPassword().equals(password)) {
				//로그인 성공 : 로그인정보 seesion에 보관 , home으로 
				request.getSession().setAttribute("loginID", vo.getId());
				request.getSession().setAttribute("loginName", vo.getName());
				mv.setViewName("redirect:home");
			}else {
			// password 오류: message, 재로그인 유도(loginForm 으로)
				mv.addObject("message","password 오류!! 다시 하세요");
				mv.setViewName("member/loginForm");
			}
		}
		else {
			// ID 오류 
			mv.addObject("message","ID 오류!! 다시 하세요");
			mv.setViewName("member/loginForm");
		}
		
		return mv;
	}// login

	// logout
		@RequestMapping(value = "logout")
		public ModelAndView logout(HttpServletRequest request, ModelAndView mv) {
			
			// 존재하는 session 확인 후 삭제 
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			mv.setViewName("redirect:home");
			return mv;
		}// logout
		//=> viewName 을 지정하지 않으면 요청명으로 찾음 -> [/WEB-INF/views/logout.jsp]
	
	
	
	
	// mdetail
	@RequestMapping(value = "mdetail")
	public ModelAndView mdetail(HttpServletRequest request, ModelAndView mv, MemberVO vo) {
		
		HttpSession session = request.getSession(false);
		if (session !=null && session.getAttribute("loginID")!=null) {
			vo.setId((String)session.getAttribute("loginID"));
			
			if(request.getParameter("id")!=null) {
				vo.setId(request.getParameter("id"));
			}
			
			vo=service.selectOne(vo);
			if (vo!=null) {
				mv.addObject("Apple",vo);
				mv.setViewName("member/memberDetail");
				
				if ("U".equals(request.getParameter("jcode"))) {
					mv.setViewName("member/updateForm");
				}else {
					mv.setViewName("member/memberDetail");
				}
				
			}else {
				mv.addObject("message","상세정보가 없습니다, 로그인 후 이용하세요 !! ");
				mv.setViewName("member/loginForm");
			}
		}else {
			// 로그인 정보가 없음.
			mv.addObject("message","상세정보가 없습니다, 로그인 후 이용하세요 !! ");
			mv.setViewName("member/loginForm");
		}
		
		return mv;
	}// mdetail

	//joinf
	@RequestMapping(value = "joinf")
	public ModelAndView joinf(ModelAndView mv) {
		
		mv.setViewName("member/joinForm");
		return mv;
	}// joinf
	
	// join
	@RequestMapping(value = "join")
	public ModelAndView logout(ModelAndView mv,  MemberVO vo) {
		
		if (service.insert(vo)> 0) {
			//join 성공 -> 로그인 유도
			mv.addObject("message","~~ 회원가입 완료, 로그인하세요~~" );
			mv.setViewName("member/loginForm");
		}else {
			// join 실페 -> 재가입 유도 
			mv.addObject("message","~~ 회원가입 오류, 다시 하세요 !! ");
			mv.setViewName("member/joinForm");
		}
		return mv;
	}// join
	
	// update
		@RequestMapping(value = "update")
		public ModelAndView update(ModelAndView mv,  MemberVO vo) {
			
			if (service.update(vo)> 0) {
				//update 성공 -> mlist
				mv.setViewName("redirect:mlist");
			}else {
				// update 실페 -> 
				mv.addObject("message"," 회원 정보 수정 실패 , 다시 하세요 !! ");
				mv.setViewName("redirect: mdetail?id="+vo.getId()+"&jcode=U");
			} 
			return mv;
		}// update
		 
	
	
}// MemberController
