package service;

import java.util.List;

import org.springframework.stereotype.Service;

import util.BoardDAO;
import vo.BoardVO;

//** interface 자동완성 
// => Alt + shift + T
// => 도는 마우스우클릭 PopUp Menu 의 Refactor - Extract Interface....

@Service
public class BoardServiceImpl implements BoardService {
	BoardDAO dao = new BoardDAO();

	@Override
	public int replyInsert(BoardVO vo) {
		return dao.replyInsert(vo);
	}

	
	@Override
	public List<BoardVO> selectList() {
		return dao.selectList();
	} // selectList

	@Override
	public BoardVO selectOne(BoardVO vo) {
		return dao.selectOne(vo);
	} // selectList

	// ** 조회수 증가
	@Override
	public int countUp(BoardVO vo) {
		return dao.countUp(vo);
	} // countUp

	@Override
	public int insert(BoardVO vo) {
		return dao.insert(vo);
	} // insert

	@Override
	public int update(BoardVO vo) {
		return dao.update(vo);
	} // update

	@Override
	public int delete(BoardVO vo) {
		return dao.delete(vo);
	} // delete

} // class
