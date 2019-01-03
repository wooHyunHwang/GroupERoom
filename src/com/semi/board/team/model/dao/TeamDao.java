package com.semi.board.team.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.semi.board.Free.model.dao.FreeDao;
import com.semi.board.Free.model.vo.Free;
import com.semi.board.notice.model.vo.Notice;
import com.semi.board.team.model.vo.Team;
import static com.semi.common.JDBCTemplate.*;

public class TeamDao {
	
	private Properties prop = new Properties();
	
	public TeamDao() {
		String fileName = FreeDao.class.getResource("/sql/board/team/team-query.properties").getPath();
	
		try {
			prop.load(new FileReader(fileName));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	
	}
	//글목록 조회
	public ArrayList<Team> selectList(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Team> list =  null;
		
		String query = prop.getProperty("selectList");
		
		System.out.println(query);
		
		try {
			stmt=con.createStatement();
			rset=stmt.executeQuery(query);

			list=new ArrayList<Team>();
			
			while(rset.next()) {
				Team t = new Team();
				
				t.setBno(rset.getInt("BOARDNO"));
				t.setbClass(rset.getString("BOARDCLASS"));
				t.setbTitle(rset.getString("BOARDTITLE"));
				t.setbContent(rset.getString("BOARDCONTENTS"));

				t.setbDate(rset.getDate("BOARDDATE"));
				t.setbClicks(rset.getInt("BOARDCLICKS"));
				t.setbAttach(rset.getString("BOARDATTACH"));
				t.setComNo(rset.getInt("COMMENTNO"));
				t.setComLevel(rset.getInt("COMMENTLEVEL"));
				t.setRecomId(rset.getString("RECOMMENTID"));
				

				t.setDeptId(rset.getString("DEPTID"));
				t.setReplebno(rset.getInt("REPLEBOARDNO"));
				t.setWriterId(rset.getString("EMPNAME"));
				t.setStatus(rset.getString("WHETHEROFDELETE"));
				t.setFile01(rset.getInt("FILE01"));
				t.setFile02(rset.getInt("FILE02"));
				t.setFile03(rset.getInt("FILE03"));
				
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}
	//글작성
	public int insertTeam(Connection con, Team t) {
		PreparedStatement pstmt = null;
		int result=0;
		String query = prop.getProperty("insertTeam");

		try {
			pstmt=con.prepareStatement(query);

			pstmt.setString(1, t.getbTitle());
			pstmt.setString(2, t.getbContent());
			pstmt.setString(3, t.getDeptId());

			pstmt.setString(4, t.getWriterId());

			System.out.println("글제목:"+t.getbTitle());
			System.out.println("글내용:"+t.getbContent());
			System.out.println("부서코드:"+t.getDeptId());
			System.out.println("작성자:"+t.getWriterId());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}
	//조회수 증가
	public int updateCount(Connection con, int num) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateCount");

		System.out.println("updateCount dao num: "+num);
		System.out.println("조회수 dao query : "+query);
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, num);
			pstmt.setInt(2, num);

			result = pstmt.executeUpdate();
			System.out.println("조회수 dao: "+result);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}
	//글 상세보기
	public Team selectOne(Connection con, int num) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Team t = null;

		String query = prop.getProperty("selectOne");
System.out.println("selectOne dao query: "+query);
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, num);

			rset=pstmt.executeQuery();

			if(rset.next()) {
				t = new Team();

				t.setBno(rset.getInt("BOARDNO"));
				t.setbClass(rset.getString("BOARDCLASS"));
				t.setbTitle(rset.getString("BOARDTITLE"));
				t.setbContent(rset.getString("BOARDCONTENTS"));
				t.setbDate(rset.getDate("BOARDDATE"));
				t.setbClicks(rset.getInt("BOARDCLICKS"));
				t.setbAttach(rset.getString("BOARDATTACH"));
				t.setComNo(rset.getInt("COMMENTNO"));
				t.setComLevel(rset.getInt("COMMENTLEVEL"));
				t.setRecomId(rset.getString("RECOMMENTID"));
				
				t.setReplebno(rset.getInt("REPLEBOARDNO"));
				t.setWriterId(rset.getString("EMPNAME"));
				t.setStatus(rset.getString("WHETHEROFDELETE"));
				t.setFile01(rset.getInt("FILE01"));
				t.setFile02(rset.getInt("FILE02"));
				t.setFile03(rset.getInt("FILE03"));




			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}


		return t;
	}
	//글삭제
	public int deleteTeam(Connection con, int bno) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteFree");
		
		System.out.println("dao query: "+query);
		System.out.println("dao bno:"+bno);
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, bno);
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
			
		return result;
	}
	
	

}
