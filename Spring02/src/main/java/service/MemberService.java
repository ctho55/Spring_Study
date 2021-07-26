package service;

import java.util.List;

import vo.MemberVO;

public interface MemberService {

	public List<MemberVO> selectListOracle();  //selectList()
	
	List<MemberVO> selectList(); //selectList()

	MemberVO selectOne(MemberVO vo); //selectOne

	int insert(MemberVO vo); //insert

	int update(MemberVO vo); //update

	int delete(MemberVO vo); //delete

}