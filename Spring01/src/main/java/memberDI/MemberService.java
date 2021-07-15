package memberDI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component("ss")
public class MemberService {
	//@Autowired
	MemberDAO dao;
	
	// => xml, 생성자 주입
	MemberService(MemberDAO dao) { 
		this.dao=dao;
		System.out.println("~~ DAO 생성자 주입 ~~");
	}
	
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