package com.ncs.green;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j;
import service.BoardService;
import vo.BoardVO;


@Log4j
@Controller
public class BoardController {

	@Autowired
	BoardService service;

	@RequestMapping(value = "/logj")
	public ModelAndView logj (ModelAndView mv , BoardVO vo) {
		
		log.info("Log4j Test :info");
		log.info("Log4j Test :error **");
		
		return mv;
	}
	
	
	
	// ** 답글달기
	// DB 의 테이블의 구조가 변경이 되어야한다. 
	// 
	
	@RequestMapping(value = "/replyf")
	public ModelAndView replyf(ModelAndView mv,BoardVO vo) {
		// => vo 에는 전달된 부모글의 root,step , indent가 담겨있음 
		// => 매핑메서드의 인자로 정의된 vo는 request.setAttribute 와 동일 scope
		// 단, 클래스명의 첫글자를 소문자로 ${boardVO.root} 
		
		mv.setViewName("board/replyForm");
		return mv;
	}// binsertf
	
	@RequestMapping(value = "/reply")
	public ModelAndView reply(ModelAndView mv, BoardVO vo,RedirectAttributes rttr) {
		
		// 답글 입력 Service 
		// => 성공이면 blist 
		// => 실패시 재입력 유도 replyForm 
		
		// => 전달된 vo 에 담겨있는 value : id,title, content 
		// => 추가적으로 필요한 value : 부모글의 root, step , indent 
		// 	  (이를 위해 boardDetail 에서 요청시 쿼리스트링으로 값 전달 - > replyf )
		// root : 부모글의 root와 동일 
		// step : 부모글의 step + 1 (기존 답글의 step 값이 이 값보다 같거나 큰 값은 + 1) : sql 로 처리 
		// indent : 부모글의 indent + 1 
		
		// => 부모글의  root, step , indent 를 jspForm에서 hidden으로 처리한 후 전송  
		// 전달된 vo 에는 이 값이 담겨있으므로 step, indent 만 1씩 증가해주면 됨.
		
		
		vo.setStep(vo.getStep()+1);
		vo.setIndent(vo.getIndent()+1);
		//부모의 step, ident 값을 댓글의 값에 맞춰 
		// 기능 실행 
		
		if (service.replyInsert(vo) > 0 ) {
			// 답글입력 성공
			rttr.addFlashAttribute("message", "~~ 답글 등록 성공");
		}else {
			// 답글입력 실패 
			rttr.addFlashAttribute("message", "~~ 답글 등록 실패");
		}
		mv.setViewName("redirect:blist");
		
		return mv;
	}// binsertf

	
	// *****************************************
		// Board CRUD
	@RequestMapping(value = "/blist")
	public ModelAndView blist(ModelAndView mv) {
		List<BoardVO> list = service.selectList();
		if (list != null) {
			mv.addObject("Banana", list);
		} else {
			mv.addObject("message", "출력할 자료가 없습니다!! ");
		}
		mv.setViewName("board/boardList");
		return mv;
	}// blist

	
	@RequestMapping(value = "/bdetail")
	public ModelAndView bdetail(ModelAndView mv, BoardVO vo, HttpServletRequest request) {
		
		//=> 로그인 했을때만 글내용을 볼 수 있도록
		HttpSession session = request.getSession(false);
		if (session!= null && session.getAttribute("loginID")!=null) {
		// 세션 유무 확인
			String loginID = (String)session.getAttribute("loginID");
			
			System.out.println("loginID=> "+loginID);
			System.out.println("request.getParameter(id)=> "+request.getParameter("id"));
			
			if (!loginID.equals(request.getParameter("id"))) {
				 // 세션에저장되있는로그인 아이디(글쓴이)와 파라미터로 받은 아이디(보는이) 비교 
				 // 다르면 조회수 증가 
				 service.countUp(vo) ;
			} 
			 vo = service.selectOne(vo);
			 if (vo != null) {
				 mv.addObject("Apple",vo);
				 mv.setViewName("board/boardDetail");
				 if("U".equals(request.getParameter("jcode"))) {
					 // 글수정 링크  
					 // jsp에서 글수정 링크 "bdetail?seq=${Apple.seq}&jcode=U"
					 mv.setViewName("board/bupdateForm");
				 } 
			 }else {
				mv.addObject("message", "글번호에 해당하는 글을 찾을수 없습니다.");
				mv.setViewName("board/boardList");
			}
		}else {
			mv.addObject("message", "로그인 정보가 없습니다.");
			mv.setViewName("board/boardList");
		}
		//=> 조회수 증가 
		// 글쓴이와 글 보는이가 달라야함
		// DAO  countUP 매서드
		
		return mv ;
	}// bdetail	
		

	@RequestMapping(value = "/bupdate")
	public ModelAndView bupdate(ModelAndView mv, BoardVO vo, RedirectAttributes rttr) {
		
		if (service.update(vo) > 0) {
			rttr.addFlashAttribute("message", "게시물이 성공적으로 완료되엇습니다.");
			mv.setViewName("redirect:blist");
		} else {
			rttr.addFlashAttribute("message", "게시물 수정이 실패하였습니다.");
			mv.setViewName("redirect:bdetail?seq=" + vo.getSeq() + "&jcode=U");
		}
		
		return mv;
	}// bupdate
	

	@RequestMapping(value = "/binsertf")
	public ModelAndView binsertf(ModelAndView mv) {

		mv.setViewName("board/binsertForm");
		return mv;
	}// binsertf

	@RequestMapping(value = "/binsert")
	public ModelAndView binsert(ModelAndView mv, BoardVO vo, HttpServletRequest request) {

		if (service.insert(vo) > 0) {
			mv.addObject("message", "insert Success");
			mv.setViewName("redirect: blist");
		}
		return mv;
	}// binsert

	@RequestMapping(value = "/bdelete")
	public ModelAndView bdelete(ModelAndView mv, BoardVO vo, RedirectAttributes rttr) {

		if (service.delete(vo) > 0) {
			rttr.addFlashAttribute("message", "게시물 삭제가 성공적으로 완료되엇습니다.");
			mv.setViewName("redirect:blist");
		} else {
			rttr.addFlashAttribute("message", "게시물 삭제하는데 실패하였습니다");
			mv.setViewName("redirect:blist");
		}
 
		return mv;
	}// bdelete

	
}// class
