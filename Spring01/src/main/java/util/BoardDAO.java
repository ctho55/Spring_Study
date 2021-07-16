package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vo.BoardVO;

// ** Board CRUD
// => selectList, selectOne, insert, update, delete 
public class BoardDAO {
	// ** 전역변수 정의
	Connection cn = DBConnection.getConnection();
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	String sql; 
	// ** selectList
	public List<BoardVO> selectList() {
		sql = "select seq,title,id,regdate,cnt from board order by seq desc";
		
		List<BoardVO> list = new ArrayList<>();
		try {
			st=cn.createStatement();
			rs=st.executeQuery(sql);
			if (rs.next()) {
				do {
					BoardVO vo = new BoardVO();
					vo.setSeq(rs.getInt(1));
					vo.setTitle(rs.getString(2));
					vo.setId(rs.getString(3));
					vo.setRegdate(rs.getString(4));
					vo.setCnt(rs.getInt(5));
					list.add(vo);
				}while(rs.next());
			}else {
				System.out.println("** 출력할 자료가 1건도 없습니다 ~ **");
				list=null;
			}
		} catch (Exception e) {
			System.out.println("** selectList => "+e.toString());
			list = null;
		}
		return list;
	} //selectList
	
	// ** selectOne
	public BoardVO selectOne(BoardVO vo) {
		sql = "select * from board where seq=?" ;
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1, vo.getSeq());
			rs=pst.executeQuery();
			if (rs.next()) {
				vo.setTitle(rs.getString(2));
				vo.setId(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setRegdate(rs.getString(5));
				vo.setCnt(rs.getInt(6));
				return vo;
			}else {
				System.out.println("** 글번호에 해당하는 글을 찾을 수 없습니다 ~ **");
			}
		} catch (Exception e) {
			System.out.println("** selectOne => "+e.toString());
		}
		return null;
	} //selectOne 
	
	// ** 조회수 증가
	public int countUp(BoardVO vo) {
		sql = "update board set cnt=cnt+1 where seq=?";
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1, vo.getSeq());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** countUp => "+e.toString());
		}
		return 0;
	} //countUp
	
	
	// ** insert
	public int insert(BoardVO vo) {
		sql="insert into board(title,id,content,regdate,cnt) values "
				+ "(?,?,?,CURRENT_TIMESTAMP,0)" ;
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, vo.getTitle());
			pst.setString(2, vo.getId());
			pst.setString(3, vo.getContent());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** Board insert => "+e.toString());
		}
		return 0;
	} //insert
	
	// ** update
	public int update(BoardVO vo) {
		sql="update board set title=?, content=? where seq=?" ;
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, vo.getTitle());
			pst.setString(2, vo.getContent());
			pst.setInt(3, vo.getSeq());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** Board update => "+e.toString());
		}
		return 0;
	} //update
	
	// ** delete
	public int delete(BoardVO vo) {
		sql="delete from board where seq=?" ;
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, vo.getSeq());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** Board update => "+e.toString());
		}
		return 0;
	} //delete

} //class
