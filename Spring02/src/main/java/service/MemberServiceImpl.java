package service;

import java.util.List;

import org.springframework.stereotype.Service;

import util.MemberDAO;
import vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	MemberDAO dao ;
	MemberServiceImpl(MemberDAO dao) { 
		this.dao=dao;
		System.out.println("~~ DAO 생성자 주입 ~~");
	}
	@Override
	public List<MemberVO> selectListOracle() {
		return dao.selectListOracle();
	}
	
	@Override
	public List<MemberVO> selectList() {
		return dao.selectList();
	} //selectList()
	@Override
	public MemberVO selectOne(MemberVO vo) {
		return dao.selectOne(vo);
	} //selectOne
	
	@Override
	public int insert(MemberVO vo) {
		return dao.insert(vo);
	} //insert
	@Override
	public int update(MemberVO vo) {
		return dao.update(vo);
	} //update
	@Override
	public int delete(MemberVO vo) {
		return dao.delete(vo);
	} //delete
} //class