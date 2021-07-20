package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import util.MemberDAO;
import vo.MemberVO;

//@Component
@Service
public class MemberService {
	
	@Autowired
	MemberDAO dao ;
	
	public List<MemberVO> selectList() {
		return dao.selectList();
	} //selectList()
	public MemberVO selectOne(MemberVO vo) {
		return dao.selectOne(vo);
	} //selectOne
	
	public int insert(MemberVO vo) {
		return dao.insert(vo);
	} //insert
	public int update(MemberVO vo) {
		return dao.update(vo);
	} //update
	public int delete(MemberVO vo) {
		return dao.delete(vo);
	} //delete
} //class