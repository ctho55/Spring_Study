package com.ncs.green;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;
import service.MemberService;
import vo.MemberVO;

@RequestMapping(value = "/member")  // "/member"로 시작하는 모든 요청을 처리함. 
@Log4j
@Controller
public class MemberController2 {
	
	@Autowired
	MemberService service;
	
	//oracle Test 
	
	@RequestMapping(value = "/list")
	public ModelAndView list(ModelAndView mv) {
		
		List<MemberVO> list = service.selectList();

		if (list != null) {
			mv.addObject("Banana", list);
		} else {
			mv.addObject("message", "!! 출력할 자료가 한건도 없습니다. !!");
		}

		mv.setViewName("member/memberList3");
		return mv;
	}// list


	// mv.setViewName("..") 생략 Test
	@RequestMapping(value = "/memberList3")
	public ModelAndView memberList3(ModelAndView mv) {
		
		List<MemberVO> list = service.selectList();
		
		if (list != null) {
			mv.addObject("Banana", list);
		} else {
			mv.addObject("message", "!! 출력할 자료가 한건도 없습니다. !!");
		}
		//mv.setViewName("member/memberList");
		// => 생략하면 요청명을 사용함.
		return mv;  
	}// list

	
}// MemberController
