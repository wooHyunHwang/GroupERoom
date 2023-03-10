package com.semi.board.Free.model.dao;

import static com.semi.common.JDBCTemplate.close;
import static com.semi.common.JDBCTemplate.commit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;


import com.semi.board.Free.model.vo.Attachment;
import com.semi.board.Free.model.vo.Free;

public class FreeDao {

	private Properties prop = new Properties();
	
	public FreeDao() {
		String fileName = FreeDao.class.getResource("/sql/board/free/free-query.properties").getPath();
	
		try {
			prop.load(new FileReader(fileName));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	
	}
/*	
	//
	public ArrayList<Free> selectList(Connection con, int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Free> list = null;
		
		String query = prop.getProperty("selectList");
		
		System.out.println(query);
		
		try {
			pstmt= con.prepareStatement(query);
			
			int startRow = (currentPage -1) * limit + 1;
			int endRow = startRow + limit -1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset=pstmt.executeQuery();
			
			list=new ArrayList<Free>();
			
			while(rset.next()) {
				Free f = new Free();
				
				f.setBno(rset.getInt("BOARDNO"));
				f.setbClass(rset.getString("BOARDCLASS"));
				f.setbTitle(rset.getString("BOARDTITLE"));
				f.setbContent(rset.getString("BOARDCONTENTS"));

				f.setbDate(rset.getDate("BOARDDATE"));
				f.setbClicks(rset.getInt("BOARDCLICKS"));
				f.setbAttach(rset.getString("BOARDATTACH"));
				f.setComNo(rset.getInt("COMMENTNO"));
				f.setComLevel(rset.getInt("COMMENTLEVEL"));
				f.setRecomId(rset.getString("RECOMMENTID"));
				

				f.setDeptId(rset.getString("DEPTID"));
				f.setReplebno(rset.getInt("REPLEBOARDNO"));
				f.setWriterId(rset.getString("EMPNAME"));
				f.setStatus(rset.getString("WHETHEROFDELETE"));
				f.setFile01(rset.getInt("FILE01"));
				f.setFile02(rset.getInt("FILE02"));
				f.setFile03(rset.getInt("FILE03"));
				
				list.add(f);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		
		
		return list;
	}

	public int getListCount(Connection con) {
		Statement stmt = null;
		int listCount = 0;
		ResultSet rset = null;
		
		String query = prop.getProperty("listCount");
		
		try {
			stmt=con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(stmt);
			close(rset);
		}
			
		return listCount;
	}*/
	//??? ?????? ??????
	/*public ArrayList<Free> selectList(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Free> list =  null;
		
		String query = prop.getProperty("selectList");
		
		System.out.println(query);
		
		try {
			stmt=con.createStatement();
			rset=stmt.executeQuery(query);

			list=new ArrayList<Free>();
			
			while(rset.next()) {
				Free f = new Free();
				
				f.setBno(rset.getInt("BOARDNO"));
				f.setbClass(rset.getString("BOARDCLASS"));
				f.setbTitle(rset.getString("BOARDTITLE"));
				f.setbContent(rset.getString("BOARDCONTENTS"));

				f.setbDate(rset.getDate("BOARDDATE"));
				f.setbClicks(rset.getInt("BOARDCLICKS"));
				f.setbAttach(rset.getString("BOARDATTACH"));
				f.setComNo(rset.getInt("COMMENTNO"));
				f.setComLevel(rset.getInt("COMMENTLEVEL"));
				f.setRecomId(rset.getString("RECOMMENTID"));
				

				f.setDeptId(rset.getString("DEPTID"));
				f.setReplebno(rset.getInt("REPLEBOARDNO"));
				f.setWriterId(rset.getString("EMPNAME"));
				f.setStatus(rset.getString("WHETHEROFDELETE"));
				f.setFile01(rset.getInt("FILE01"));
				f.setFile02(rset.getInt("FILE02"));
				f.setFile03(rset.getInt("FILE03"));
				
				list.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}
*/
	//??? ??????
		public int insertFree(Connection con, Free f) {
			PreparedStatement pstmt = null;
			int result=0;
			String query = prop.getProperty("insertFree");

			try {
				pstmt=con.prepareStatement(query);

				pstmt.setString(1, f.getbTitle());
				pstmt.setString(2, f.getbContent());
				pstmt.setString(3, f.getDeptId());
				pstmt.setString(4, f.getWriterId());

				System.out.println(f.getbTitle());
				System.out.println(f.getbContent());
				System.out.println(f.getWriterId());

				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstmt);
			}

			return result;
		}

		//????????? ??????
		public int updateCount(Connection con, int num) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = prop.getProperty("updateCount");

			try {
				pstmt=con.prepareStatement(query);
				pstmt.setInt(1, num);
				pstmt.setInt(2, num);

				result = pstmt.executeUpdate();
				System.out.println("dao result : "+result);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}

			return result;
		}
		//??? ????????????(?????????)
		public HashMap<String, Object> selectOne(Connection con, int num) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			Free f = null;
			HashMap<String, Object> hmap = null;
			Attachment at = null;
			
			String query = prop.getProperty("selectOne");
System.out.println("???????????? dao : "+query);
			try {
				pstmt=con.prepareStatement(query);
				pstmt.setInt(1, num);

				rset=pstmt.executeQuery();

				if(rset.next()) {
					f = new Free();

					f.setBno(rset.getInt("BOARDNO"));
					f.setbClass(rset.getString("BOARDCLASS"));
					f.setbTitle(rset.getString("BOARDTITLE"));
					f.setbContent(rset.getString("BOARDCONTENTS"));
					f.setbDate(rset.getDate("BOARDDATE"));
					f.setbClicks(rset.getInt("BOARDCLICKS"));
					f.setbAttach(rset.getString("BOARDATTACH"));
					f.setComNo(rset.getInt("COMMENTNO"));
					f.setComLevel(rset.getInt("COMMENTLEVEL"));
					f.setRecomId(rset.getString("RECOMMENTID"));
					
					f.setReplebno(rset.getInt("REPLEBOARDNO"));
					f.setWriterId(rset.getString("EMPNAME"));
					f.setStatus(rset.getString("WHETHEROFDELETE"));
					f.setFile01(rset.getInt("FILE01"));
					f.setFile02(rset.getInt("FILE02"));
					f.setFile03(rset.getInt("FILE03"));
					
					
						at = new Attachment();
						at.setAno(rset.getInt("ATTACHNO"));
						at.setOriginName(rset.getString("ATTACHPRENAME"));
						at.setChangeName(rset.getString("ATTACHNAME"));
						at.setFilePath(rset.getString("ATTACHPATH"));
						at.setUploadDate(rset.getDate("ATTACHDAY"));
						at.setWhetherofDelete(rset.getString("WHETHEROFDELETE"));
						
						if(rset == null) {
							query = prop.getProperty("selectOneNoFile");
							System.out.println("???????????? ?????? dao query: "+query);
							
								pstmt=con.prepareStatement(query);
								pstmt.setInt(1, num);

								rset=pstmt.executeQuery();

								if(rset.next()) {
									f = new Free();

									f.setBno(rset.getInt("BOARDNO"));
									f.setbClass(rset.getString("BOARDCLASS"));
									f.setbTitle(rset.getString("BOARDTITLE"));
									f.setbContent(rset.getString("BOARDCONTENTS"));
									f.setbDate(rset.getDate("BOARDDATE"));
									f.setbClicks(rset.getInt("BOARDCLICKS"));
									f.setbAttach(rset.getString("BOARDATTACH"));
									f.setComNo(rset.getInt("COMMENTNO"));
									f.setComLevel(rset.getInt("COMMENTLEVEL"));
									f.setRecomId(rset.getString("RECOMMENTID"));
									
									f.setReplebno(rset.getInt("REPLEBOARDNO"));
									f.setWriterId(rset.getString("EMPNAME"));
									f.setStatus(rset.getString("WHETHEROFDELETE"));
									f.setFile01(rset.getInt("FILE01"));
									f.setFile02(rset.getInt("FILE02"));
									f.setFile03(rset.getInt("FILE03"));
									
						}
								hmap = new HashMap<String, Object>();
								hmap.put("Free", f);
						}else {
					
				}
					
				
				
				hmap = new HashMap<String, Object>();
				
				hmap.put("Free", f);
				hmap.put("attachment", at);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rset);
			}


			return hmap;
		}
		//????????? ?????? ??? ????????? ??????
		public ArrayList<Free> selectList(Connection con, int currentPage, int limit) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<Free> list = null;
			
			String query = prop.getProperty("selectList");
			
			try {
				pstmt = con.prepareStatement(query);
				
				int startRow = (currentPage - 1) * limit + 1;
				int endRow = startRow + limit - 1;
				
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				
				rset=pstmt.executeQuery();
				
				list= new ArrayList<Free>();
				
				while(rset.next()) {
					
				
				Free f = new Free();
				
				f.setBno(rset.getInt("BOARDNO"));
				f.setbClass(rset.getString("BOARDCLASS"));
				f.setbTitle(rset.getString("BOARDTITLE"));
				f.setbContent(rset.getString("BOARDCONTENTS"));
				f.setbDate(rset.getDate("BOARDDATE"));
				f.setbClicks(rset.getInt("BOARDCLICKS"));
				f.setbAttach(rset.getString("BOARDATTACH"));
				f.setComNo(rset.getInt("COMMENTNO"));
				f.setComLevel(rset.getInt("COMMENTLEVEL"));
				f.setRecomId(rset.getString("RECOMMENTID"));
				
				f.setReplebno(rset.getInt("REPLEBOARDNO"));
				f.setWriterId(rset.getString("EMPNAME"));
				f.setStatus(rset.getString("WHETHEROFDELETE"));
				f.setFile01(rset.getInt("FILE01"));
				f.setFile02(rset.getInt("FILE02"));
				f.setFile03(rset.getInt("FILE03"));
				
				list.add(f);
				}
				System.out.println("dao list: "+list.size());
				

			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rset);
			}
			
			return list;
		}
		
		public int getlistCount(Connection con) {
			Statement stmt = null;
			int listCount = 0;
			ResultSet rset = null;
			
			String query = prop.getProperty("listCount");
			
			try {
				stmt = con.createStatement();
				rset = stmt.executeQuery(query);
				
				if(rset.next()) {
					listCount = rset.getInt(1);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(stmt);
				close(rset);
			}
				
			return listCount;
		}
		
		//??? ????????????
		public int updateFree(Connection con, Free f) {
			PreparedStatement pstmt = null;
			int result=0;
			String query = prop.getProperty("updateFree");
			System.out.println("update??????:"+query);
			System.out.println("1, f.getbTitle(): "+f.getbTitle());
			System.out.println("2, f.getbContent() : "+f.getbContent());
			System.out.println("3, f.getFile02() :"+f.getFile02());
			System.out.println("4, f.getBno(): "+f.getBno());
			try {
				pstmt=con.prepareStatement(query);
				pstmt.setString(1, f.getbTitle());
				pstmt.setString(2, f.getbContent());
			
				pstmt.setInt(3, f.getFile02());
				pstmt.setInt(4, f.getBno());
				
				result=pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstmt);
			}

			return result;
		}
		//?????????
		public int deleteFree(Connection con, int bno) {
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
		//??????
		public HashMap<String, Object> editOne(Connection con, int num) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			Free f = null;
			HashMap<String, Object> hmap = null;
			Attachment at = null;
			 
			String query = prop.getProperty("selectOne");
			
			try {
				pstmt=con.prepareStatement(query);
				pstmt.setInt(1, num);
				
				rset=pstmt.executeQuery();
				
				if(rset.next()) {
					f = new Free();

					f.setBno(rset.getInt("BOARDNO"));
					f.setbClass(rset.getString("BOARDCLASS"));
					f.setbTitle(rset.getString("BOARDTITLE"));
					f.setbContent(rset.getString("BOARDCONTENTS"));

					f.setbDate(rset.getDate("BOARDDATE"));
					f.setbClicks(rset.getInt("BOARDCLICKS"));
					f.setbAttach(rset.getString("BOARDATTACH"));
					f.setComNo(rset.getInt("COMMENTNO"));
					f.setComLevel(rset.getInt("COMMENTLEVEL"));
					f.setRecomId(rset.getString("RECOMMENTID"));
					

					f.setDeptId(rset.getString("DEPTID"));
					f.setReplebno(rset.getInt("REPLEBOARDNO"));
					f.setWriterId(rset.getString("EMPNAME"));
					f.setStatus(rset.getString("WHETHEROFDELETE"));
					f.setFile01(rset.getInt("FILE01"));
					f.setFile02(rset.getInt("FILE02"));
					f.setFile03(rset.getInt("FILE03"));
					
					at = new Attachment();
					at.setAno(rset.getInt("ATTACHNO"));
					at.setOriginName(rset.getString("ATTACHPRENAME"));
					at.setChangeName(rset.getString("ATTACHNAME"));
					at.setFilePath(rset.getString("ATTACHPATH"));
					at.setUploadDate(rset.getDate("ATTACHDAY"));
					at.setWhetherofDelete(rset.getString("WHETHEROFDELETE"));
					
				}
				
				hmap = new HashMap<String, Object>();
				
				hmap.put("Free", f);
				hmap.put("attachment", at);
				
				
				
			} catch (SQLException e) {				
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rset);
			}
	
			return hmap;
		}
		//?????? ??????
		public int insertReply(Connection con, Free f) {
			PreparedStatement pstmt = null;
			
			int result = 0;
			String query = prop.getProperty("insertReply");
			System.out.println("dao????????????: "+query);
			try {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, f.getbContent());
				pstmt.setInt(2, f.getBno());
				pstmt.setString(3, f.getWriterId());
				
				result=pstmt.executeUpdate();
				
				System.out.println("dao ??????");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			
			
			return result;
		}
		//?????? ?????????
		public ArrayList<Free> selectReplyList(Connection con, int bno) {
			PreparedStatement pstmt= null;
			ResultSet rset = null;
			ArrayList<Free> list=null;
			
			String query = prop.getProperty("selectReplyList");
			System.out.println("dao ?????? ?????? : "+query);
			try {
				pstmt=con.prepareStatement(query);
				pstmt.setInt(1, bno);
				
				rset=pstmt.executeQuery();
				
				list=new ArrayList<Free>();
				
				while(rset.next()) {
					Free f = new Free();
					
					f.setBno(rset.getInt("BOARDNO"));
					f.setbContent(rset.getString("BOARDCONTENTS"));
					f.setWriterId(rset.getString("EMPNAME"));
					f.setComNo(rset.getInt("COMMENTNO"));
					f.setComLevel(rset.getInt("COMMENTLEVEL"));
					f.setbDate(rset.getDate("BOARDDATE"));
					
					list.add(f);
	
				}
				System.out.println("dao ???????????????: "+list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rset);
			}
			 
			
			return list;
		}
		//???????????? ??????
		/*public ArrayList<Free> searchName(Connection con, String userName) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<Free> list = null;
			
			String query = prop.getProperty("searchName");
			
			try {
				pstmt=con.prepareStatement(query);
				pstmt.setString(1, userName);
				
				rset=pstmt.executeQuery();
				
				list = new ArrayList<Free>();
				
				while(rset.next()) {
					Free f = new Free();
					
					f.setBno(rset.getInt("BOARDNO"));
					f.setbClass(rset.getString("BOARDCLASS"));
					f.setbTitle(rset.getString("BOARDTITLE"));
					f.setbContent(rset.getString("BOARDCONTENTS"));
					f.setbDate(rset.getDate("BOARDDATE"));
					f.setbClicks(rset.getInt("BOARDCLICKS"));
					f.setbAttach(rset.getString("BOARDATTACH"));
					f.setComNo(rset.getInt("COMMENTNO"));
					f.setComLevel(rset.getInt("COMMENTLEVEL"));
					f.setRecomId(rset.getString("RECOMMENTID"));
					
					f.setReplebno(rset.getInt("REPLEBOARDNO"));
					f.setWriterId(rset.getString("EMPNAME"));
					f.setStatus(rset.getString("WHETHEROFDELETE"));
					f.setFile01(rset.getInt("FILE01"));
					f.setFile02(rset.getInt("FILE02"));
					f.setFile03(rset.getInt("FILE03"));
					
					list.add(f);
					
				}
				System.out.println("dao listsize:"+list.size());

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rset);
			}
			
			return list;
		}*/
		//??????????????? ??????
		/*public ArrayList<Free> searchTitle(Connection con, String title) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<Free> list = null;
			
			String query = prop.getProperty("searchTitle");
			
			try {
				pstmt=con.prepareStatement(query);
				pstmt.setString(1, title);
				
				rset=pstmt.executeQuery();
				
				list = new ArrayList<Free>();
				
				while(rset.next()) {
					Free f = new Free();
					
					f.setBno(rset.getInt("BOARDNO"));
					f.setbClass(rset.getString("BOARDCLASS"));
					f.setbTitle(rset.getString("BOARDTITLE"));
					f.setbContent(rset.getString("BOARDCONTENTS"));
					f.setbDate(rset.getDate("BOARDDATE"));
					f.setbClicks(rset.getInt("BOARDCLICKS"));
					f.setbAttach(rset.getString("BOARDATTACH"));
					f.setComNo(rset.getInt("COMMENTNO"));
					f.setComLevel(rset.getInt("COMMENTLEVEL"));
					f.setRecomId(rset.getString("RECOMMENTID"));
					
					f.setReplebno(rset.getInt("REPLEBOARDNO"));
					f.setWriterId(rset.getString("EMPNAME"));
					f.setStatus(rset.getString("WHETHEROFDELETE"));
					f.setFile01(rset.getInt("FILE01"));
					f.setFile02(rset.getInt("FILE02"));
					f.setFile03(rset.getInt("FILE03"));
					
					list.add(f);
					
				}
				System.out.println("dao listsize:"+list.size());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rset);
			}
			
			return list;
		}*/
		//???????????? ????????????
		public ArrayList<Free> searchName(String userName, Connection con, int currentPage,int maxPage, int limit) {
			PreparedStatement pstmt=null;
			ResultSet rset=null;
			ArrayList<Free> list=null;
			System.out.println("dao userName: "+userName);
			System.out.println("dao currentPage: "+currentPage);
			String query=prop.getProperty("searchName");
			
			System.out.println("dao query: "+query);
			try {
				pstmt=con.prepareStatement(query);
			
				
				int startRow = (currentPage - 1) * limit + 1; // ????????? ??? ????????? ??? ??????
				int endRow = startRow + limit - 1;
				
				System.out.println("dao startRow: "+startRow);
				System.out.println("dao endRow: "+endRow);
				pstmt.setInt(1, 1);
				pstmt.setInt(2, limit*maxPage);
				pstmt.setString(3, userName);
				
				rset=pstmt.executeQuery();
				list=new ArrayList<Free>();
				
				while(rset.next()) {
					Free f=new Free();
					
					f.setBno(rset.getInt("BOARDNO"));
					f.setbClass(rset.getString("BOARDCLASS"));
					f.setbTitle(rset.getString("BOARDTITLE"));
					f.setbContent(rset.getString("BOARDCONTENTS"));
					f.setbDate(rset.getDate("BOARDDATE"));
					f.setbClicks(rset.getInt("BOARDCLICKS"));
					f.setbAttach(rset.getString("BOARDATTACH"));
					f.setComNo(rset.getInt("COMMENTNO"));
					f.setComLevel(rset.getInt("COMMENTLEVEL"));
					f.setRecomId(rset.getString("RECOMMENTID"));
					
					f.setReplebno(rset.getInt("REPLEBOARDNO"));
					f.setWriterId(rset.getString("EMPNAME"));
					f.setStatus(rset.getString("WHETHEROFDELETE"));
					f.setFile01(rset.getInt("FILE01"));
					f.setFile02(rset.getInt("FILE02"));
					f.setFile03(rset.getInt("FILE03"));
					if(rset.getInt("ROWNUM") >=startRow && rset.getInt("ROWNUM") <=endRow) {
						list.add(f);
					}
					
					
				}
				System.out.println("name??????dao list: "+list.size());

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return list;
		}
		//??????????????? ?????? ?????? ?????????
		public ArrayList<Free> searchTitle(String title, Connection con, int currentPage, int maxPage,int limit) {
			PreparedStatement pstmt=null;
			ResultSet rset=null;
			ArrayList<Free> list=null;
			System.out.println("dao userName: "+title);
			System.out.println("dao currentPage: "+currentPage);
			String query=prop.getProperty("searchTitle");
			
			System.out.println("dao query: "+query);
			try {
				pstmt=con.prepareStatement(query);
			
				
				int startRow = (currentPage - 1) * limit + 1; // ????????? ??? ????????? ??? ??????
				int endRow = startRow + limit - 1;
				
				System.out.println("dao startRow: "+startRow);
				System.out.println("dao endRow: "+endRow);
				pstmt.setInt(1, 1);
				pstmt.setInt(2, limit*maxPage);
				pstmt.setString(3, title);
				
				rset=pstmt.executeQuery();
				list=new ArrayList<Free>();
				
				while(rset.next()) {
					Free f=new Free();
					
					f.setBno(rset.getInt("BOARDNO"));
					f.setbClass(rset.getString("BOARDCLASS"));
					f.setbTitle(rset.getString("BOARDTITLE"));
					f.setbContent(rset.getString("BOARDCONTENTS"));
					f.setbDate(rset.getDate("BOARDDATE"));
					f.setbClicks(rset.getInt("BOARDCLICKS"));
					f.setbAttach(rset.getString("BOARDATTACH"));
					f.setComNo(rset.getInt("COMMENTNO"));
					f.setComLevel(rset.getInt("COMMENTLEVEL"));
					f.setRecomId(rset.getString("RECOMMENTID"));
					
					f.setReplebno(rset.getInt("REPLEBOARDNO"));
					f.setWriterId(rset.getString("EMPNAME"));
					f.setStatus(rset.getString("WHETHEROFDELETE"));
					f.setFile01(rset.getInt("FILE01"));
					f.setFile02(rset.getInt("FILE02"));
					f.setFile03(rset.getInt("FILE03"));
					if(rset.getInt("ROWNUM") >=startRow && rset.getInt("ROWNUM") <=endRow) {
						list.add(f);
					}
					
					
				}
				System.out.println("title??????dao list: "+list.size());

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return list;
		}
		//???????????? ??? ?????? ????????? ??? 
		public int getSearchTitleListCount(Connection con, String title) {
			PreparedStatement pstmt = null;
			int listCount = 0;
			ResultSet rset = null;
			
			String query = prop.getProperty("searchTitleListCount");
			
			try {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, title);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					listCount = rset.getInt(1);
				}
				System.out.println("dao listCount:"+listCount);

			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rset);
			}
				
			return listCount;
		}
		//?????? ?????? ?????? ????????? ???
		public int getSearchNameListCount(Connection con, String userName) {
			PreparedStatement pstmt = null;
			int listCount = 0;
			ResultSet rset = null;
			
			String query = prop.getProperty("searchNameListCount");
			
			try {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, userName);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					listCount = rset.getInt(1);
				}
				System.out.println("dao listCount: "+listCount);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rset);
			}
				
			return listCount;
		}
		//??????????????????
		/*public int selectCurrval(Connection con) {
			Statement stmt = null;
			ResultSet rset = null;
			
			int ano = 0;
			
			String query = prop.getProperty("selectCurrval");
			
			try {
				stmt=con.createStatement();
				
				rset=stmt.executeQuery(query);
				
				if(rset.next()) {
					ano = rset.getInt("CURRVAL");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(stmt);
				close(rset);
			}
			
			return ano;
		}*/
		//???????????? ???????????? ??? ??????
		/*public int insertAttachment(Connection con, ArrayList<Attachment> fileList) {
			PreparedStatement pstmt = null;
			int result=0;
			
			String query = prop.getProperty("insertAttachment");
			
			
				try {
					
					for(int i=0; i < fileList.size(); i++) {
						pstmt=con.prepareStatement(query);
						
						pstmt.setString(1, fileList.get(i).getOriginName());
						pstmt.setString(2, fileList.get(i).getChangeName());
						pstmt.setString(3, fileList.get(i).getFilePath());
						
						result += pstmt.executeUpdate();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					close(pstmt);
			}
			
			return result;
		}*/
		//??? ??????????????? ?????? ?????? ????????????
		public ArrayList<Free> selectReply(Connection con, int num) {
			PreparedStatement pstmt = null;
			ArrayList<Free> reply = null;
			ResultSet rset = null;
			Free f = null;

			String query = prop.getProperty("selectReply");
			System.out.println(num);
			System.out.println(query);
			try {
				pstmt=con.prepareStatement(query);
				pstmt.setInt(1, num);
				
				rset=pstmt.executeQuery();
				
			
					reply= new ArrayList<Free>();
					
					while(rset.next()) {
						f= new Free();
					
					
					f.setBno(rset.getInt("BOARDNO"));
					f.setbContent(rset.getString("BOARDCONTENTS"));
					f.setbDate(rset.getDate("BOARDDATE"));
					
					f.setReplebno(rset.getInt("REPLEBOARDNO"));
					f.setWriterId(rset.getString("EMPNAME"));
				
					reply.add(f);
					}
								
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rset);
			}
			
			return reply;
		}
		//??? ???????????? ?????? ?????? ?????????
		public ArrayList<Free> searchContent(String content, Connection con, int currentPage, int maxPage, int limit) {
			PreparedStatement pstmt=null;
			ResultSet rset=null;
			ArrayList<Free> list=null;
			System.out.println("dao content: "+content);
			System.out.println("dao currentPage: "+currentPage);
			String query=prop.getProperty("searchContent");
			
			System.out.println("dao query: "+query);
			try {
				pstmt=con.prepareStatement(query);
			
				
				int startRow = (currentPage - 1) * limit + 1; // ????????? ??? ????????? ??? ??????
				int endRow = startRow + limit - 1;
				
				System.out.println("dao startRow: "+startRow);
				System.out.println("dao endRow: "+endRow);
				pstmt.setInt(1, 1);
				pstmt.setInt(2, limit*maxPage);
				pstmt.setString(3, content);
				
				rset=pstmt.executeQuery();
				list=new ArrayList<Free>();
				
				while(rset.next()) {
					Free f=new Free();
					
					f.setBno(rset.getInt("BOARDNO"));
					f.setbClass(rset.getString("BOARDCLASS"));
					f.setbTitle(rset.getString("BOARDTITLE"));
					f.setbContent(rset.getString("BOARDCONTENTS"));
					f.setbDate(rset.getDate("BOARDDATE"));
					f.setbClicks(rset.getInt("BOARDCLICKS"));
					f.setbAttach(rset.getString("BOARDATTACH"));
					f.setComNo(rset.getInt("COMMENTNO"));
					f.setComLevel(rset.getInt("COMMENTLEVEL"));
					f.setRecomId(rset.getString("RECOMMENTID"));
					
					f.setReplebno(rset.getInt("REPLEBOARDNO"));
					f.setWriterId(rset.getString("EMPNAME"));
					f.setStatus(rset.getString("WHETHEROFDELETE"));
					f.setFile01(rset.getInt("FILE01"));
					f.setFile02(rset.getInt("FILE02"));
					f.setFile03(rset.getInt("FILE03"));
					if(rset.getInt("ROWNUM") >=startRow && rset.getInt("ROWNUM") <=endRow) {
						list.add(f);
					}
					
					
				}
				System.out.println("content??????dao list: "+list.size());

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return list;
		}
		//??????????????? ?????? ?????? ????????? ???
		public int getSearchContentListCount(Connection con, String content) {
			PreparedStatement pstmt = null;
			int listCount = 0;
			ResultSet rset = null;
			
			String query = prop.getProperty("searchContentListCount");
			
			try {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, content);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					listCount = rset.getInt(1);
				}
				System.out.println("dao listCount:"+listCount);

			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rset);
			}
				
			return listCount;
		}
		//????????? ??? ??????
		public int selectCurrval(Connection con) {
			Statement stmt = null;
			ResultSet rset = null;
			//select????????? ????????? ResultSet!!

			int ano =0;

			String query = prop.getProperty("selectCurrval");
System.out.println("???????????? ???????????? : "+query);
			try {

			stmt=con.createStatement();

			rset=stmt.executeQuery(query);

			if(rset.next()) {
				ano = rset.getInt("CURRVAL");
			}
			System.out.println("?????????dao??? ano : "+ano);
			} catch (SQLException e) {
			e.printStackTrace();

			}finally {

			close(stmt);
			close(rset);
			//????????? close(con)???????????? service?????? ???????????? ????????? ????????? ????????? ???~~!!!

			}

			return ano;
		}
		//???????????? ??????
		public int insertThumbnailContent(Connection con, Free f) {
			PreparedStatement pstmt = null;

			int result=0;

			String query = prop.getProperty("insertThumb");
System.out.println("BOARD??? ?????? dao query: "+query);
			try {

			pstmt=con.prepareStatement(query);

			pstmt.setString(1, f.getbTitle());
			pstmt.setString(2, f.getbContent());
			pstmt.setString(3, f.getDeptId());
			pstmt.setInt(4, Integer.parseInt(f.getWriterId()));
			pstmt.setInt(5, f.getFile01());
			result = pstmt.executeUpdate();

			} catch (SQLException e) {

			e.printStackTrace();

			}finally {

			close(pstmt);
			commit(con);
			}

			return result;
		}
		//???????????? ??????2
		public int insertAttachment(Connection con, ArrayList<Attachment> fileList) {
			PreparedStatement pstmt = null;
			int result=0;
			String query = prop.getProperty("insertAttachment");
			System.out.println("query2 "+query);
			System.out.println("insertAttachment dao fileList ????????? : "+fileList.size());
			try {

			for(int i=0; i < fileList.size(); i++) {

			pstmt=con.prepareStatement(query);

			
			pstmt.setString(1, fileList.get(i).getOriginName());
			pstmt.setString(2, fileList.get(i).getChangeName());
			pstmt.setString(3, fileList.get(i).getFilePath());

			

			result += pstmt.executeUpdate(); //'='?????? ??????

			}

			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}finally {
			close(pstmt);
			}

			return result;
		}
		//????????????
		public Attachment selectOneAttachment(Connection con, int num) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			Attachment file = null;
			
			String query = prop.getProperty("selectOneAttachment");
			
			try {
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, num);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					file = new Attachment();
					
					file.setAno(rset.getInt("ATTACHNO"));
					file.setOriginName(rset.getString("ATTACHPRENAME"));
					
					file.setChangeName(rset.getString("ATTACHNAME"));
					file.setFilePath(rset.getString("ATTACHPATH"));
					file.setUploadDate(rset.getDate("ATTACHDAY"));
					file.setWhetherofDelete(rset.getString("WHETHEROFDELETE"));
					
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rset);
			}	
			
			return file;
		}
		
		public int updateAttachment(Connection con, Attachment at) {
			PreparedStatement pstmt = null;
			int result=0;
			String query = prop.getProperty("updateAttachment");
			System.out.println("updateAttachment dao query: "+query);
			try {

			

			pstmt=con.prepareStatement(query);

			
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());


			result = pstmt.executeUpdate(); 

		

			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}finally {
			close(pstmt);
			}

			return result;
		}
		public int deleteAttachment(Connection con, int originAno) {
			PreparedStatement pstmt = null;
			int result =0;
			String query = prop.getProperty("deleteAttachment");
			System.out.println("deleteAttachment dao query: "+query);
			try {
				pstmt=con.prepareStatement(query);
				pstmt.setInt(1, originAno);
				
				result=pstmt.executeUpdate();
				System.out.println("deleteAttachment dao result: "+result);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			
			
			return result;
		}
		public int deleteOriginFile(Connection con, int originAno) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int result=0;
			String query = prop.getProperty("selectOneAttachment");
			System.out.println("deleteOriginFile dao query :" +query);
			try {
				pstmt=con.prepareStatement(query);
				pstmt.setInt(1, originAno);
				
				result=pstmt.executeUpdate();
			/*	if(rset.next()) {
					File deleteFile = new File(rset.getString("ATTACHPATH") + rset.getString("ATTACHNAME"));	

					deleteFile.delete();
					if(deleteFile.exists()) {
						
					}else {
						result=1;
					}
				}*/
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			return result;
		}
		//??? ???????????? ??????
	/*	public HashMap<String, Object> selectOne(Connection con, int num) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			Free f = null;
			HashMap<String, Object> hmap = null;
			Attachment at = null;
			
			String query = prop.getProperty("selectOneNoFile");
			System.out.println("?????? dao query ??????: "+query);

			try {
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, num);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					 f=new Free();
					
					f.setBno(rset.getInt("BOARDNO"));
					f.setbClass(rset.getString("BOARDCLASS"));
					f.setbTitle(rset.getString("BOARDTITLE"));
					f.setbContent(rset.getString("BOARDCONTENTS"));
					f.setbDate(rset.getDate("BOARDDATE"));
					f.setbClicks(rset.getInt("BOARDCLICKS"));
					f.setbAttach(rset.getString("BOARDATTACH"));
					f.setComNo(rset.getInt("COMMENTNO"));
					f.setComLevel(rset.getInt("COMMENTLEVEL"));
					f.setRecomId(rset.getString("RECOMMENTID"));
					
					f.setReplebno(rset.getInt("REPLEBOARDNO"));
					f.setWriterId(rset.getString("EMPNAME"));
					f.setStatus(rset.getString("WHETHEROFDELETE"));
					f.setFile01(rset.getInt("FILE01"));
					f.setFile02(rset.getInt("FILE02"));
					f.setFile03(rset.getInt("FILE03"));
				}
				hmap = new HashMap<String, Object>();
				
				hmap.put("Free", f);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rset);
			}

			
			return hmap;
		}*/
		//???????????? ?????? ??? ????????????
		public Free selectOneNoFile(Connection con, int num) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			Free f = null;
			
			String query = prop.getProperty("selectOneNoFile");
			System.out.println("???????????? ?????? ??? ???????????? dao query: "+query);
			try {
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, num);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					 f=new Free();
						
						f.setBno(rset.getInt("BOARDNO"));
						f.setbClass(rset.getString("BOARDCLASS"));
						f.setbTitle(rset.getString("BOARDTITLE"));
						f.setbContent(rset.getString("BOARDCONTENTS"));
						f.setbDate(rset.getDate("BOARDDATE"));
						f.setbClicks(rset.getInt("BOARDCLICKS"));
						f.setbAttach(rset.getString("BOARDATTACH"));
						f.setComNo(rset.getInt("COMMENTNO"));
						f.setComLevel(rset.getInt("COMMENTLEVEL"));
						f.setRecomId(rset.getString("RECOMMENTID"));
						
						f.setReplebno(rset.getInt("REPLEBOARDNO"));
						f.setWriterId(rset.getString("EMPNAME"));
						f.setStatus(rset.getString("WHETHEROFDELETE"));
						f.setFile01(rset.getInt("FILE01"));
						f.setFile02(rset.getInt("FILE02"));
						f.setFile03(rset.getInt("FILE03"));
				}
				System.out.println("????????? dao ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rset);
			}

			
			return f;
		}
		//????????? ??? ??????
		public Free editNoFile(Connection con, int num) {
			PreparedStatement pstmt=null;
			ResultSet rset=null;
			Free f=null;
			
			String query=prop.getProperty("selectNoFile");
			System.out.println("????????? ??????????????? ?????? ??? dao query: "+query);
			try {
				pstmt=con.prepareStatement(query);
				pstmt.setInt(1, num);
				rset=pstmt.executeQuery();
				
				if(rset.next()) {
					 f=new Free();
						
						f.setBno(rset.getInt("BOARDNO"));
						f.setbClass(rset.getString("BOARDCLASS"));
						f.setbTitle(rset.getString("BOARDTITLE"));
						f.setbContent(rset.getString("BOARDCONTENTS"));
						f.setbDate(rset.getDate("BOARDDATE"));
						f.setbClicks(rset.getInt("BOARDCLICKS"));
						f.setbAttach(rset.getString("BOARDATTACH"));
						f.setComNo(rset.getInt("COMMENTNO"));
						f.setComLevel(rset.getInt("COMMENTLEVEL"));
						f.setRecomId(rset.getString("RECOMMENTID"));
						
						f.setReplebno(rset.getInt("REPLEBOARDNO"));
						f.setWriterId(rset.getString("EMPNAME"));
						f.setStatus(rset.getString("WHETHEROFDELETE"));
						f.setFile01(rset.getInt("FILE01"));
						f.setFile02(rset.getInt("FILE02"));
						f.setFile03(rset.getInt("FILE03"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return f;
		}
		//???????????? ?????? ??? ??????
		public int updateNoFileFree(Connection con, Free f) {
			PreparedStatement pstmt=null;
			int result=0;
			
			String query=prop.getProperty("updateNoFile");
			System.out.println("???????????? ?????? ??? ?????? dao");
			try {
				pstmt=con.prepareStatement(query);
				pstmt.setString(1, f.getbTitle());
				pstmt.setString(2, f.getbContent());
				pstmt.setInt(3, f.getBno());
				
				result=pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
		}
		
		
}
