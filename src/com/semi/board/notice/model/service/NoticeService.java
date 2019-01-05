package com.semi.board.notice.model.service;

import static com.semi.common.JDBCTemplate.close;
import static com.semi.common.JDBCTemplate.commit;
import static com.semi.common.JDBCTemplate.getConnection;
import static com.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.semi.board.Free.model.dao.FreeDao;
import com.semi.board.Free.model.vo.Free;
import com.semi.board.notice.model.dao.NoticeDao;
import com.semi.board.notice.model.vo.Notice;

public class NoticeService {

	//공지사항 등록
	public int insertNotice(Notice n) {
		Connection con = getConnection();
		
		int result = new NoticeDao().insertNotice(con, n);
		
		System.out.println("service");
		if(result > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		return result;
	}
	//공지사항 리스트 불러오기
	public ArrayList<Notice> selectList() {
		Connection con = getConnection();
		System.out.println("service ");
		
		ArrayList<Notice> list = new NoticeDao().selectList(con);
		System.out.println("NoticeService list: "+list.size());
		close(con);
		
		return list;
		
		
	}
	//글 상세보기
	public Notice selectOne(int num) {
		Connection con = getConnection();
		Notice n = null;
		
		int result = new NoticeDao().updateCount(con, num);

		if(result > 0) {
			commit(con);
			n = new NoticeDao().selectOne(con, num);
		}else {
			rollback(con);
		}
		close(con);
		
		
		return n;
		
		
		
	}
	//글 삭제
	public int deleteNotice(int bno) {
		Connection con = getConnection();
		
		int result = new NoticeDao().deleteNotice(con, bno);
		
		System.out.println("service bno: "+bno);
		if(result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		return result;
	}
	//전체 글 수 조회
	public int getListCount() {
		Connection con = getConnection();
		
		int listCount = new NoticeDao().getlistCount(con);
		
		close(con);
		
		return listCount;
	}
	//페이징 처리 후 글 목록 조회
	public ArrayList<Notice> selectList(int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Notice> list = new NoticeDao().selectList(con, currentPage, limit);
		
		System.out.println("service: "+list.size());
		close(con);
		
		return list;
	}
	//글 수정
	public Notice selectOne(String num) {
		Connection con = getConnection();
		
		Notice n = new NoticeDao().selectOne(con, num);
		
		int result = 0;
		
		if(n != null) {
			result = new NoticeDao().updateCount(con, n.getBno());
			if(result > 0) commit(con);
			else rollback(con);	
			
		}
		
		close(con);
		
		return n;
	}
	//수정용
	public int updateNotice(Notice n) {
		Connection con = getConnection();
		
		int result = new NoticeDao().updateNotice(con, n);
		
		if(result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		
		return result;
	}
	//댓글 작성
	public ArrayList<Notice> insertReply(Notice n) {
		Connection con = getConnection();
		ArrayList<Notice> replyList = null;
		
		int result= new NoticeDao().insertReply(con, n);
		
		System.out.println("service: "+replyList);
		
		if(result > 0) {
			
			commit(con);
			replyList = new NoticeDao().selectReplyList(con, n.getBno());
			//=>20번글을 보고 있으면 20번글에 대한 댓글만 조회할 수 있도록
		}else {
			rollback(con);
		}
		
		close(con);
		
		return replyList;
	}
	//글 클릭하면 댓글 목록 조회
	public ArrayList<Notice> selectReply(int num) {
		Connection con = getConnection();
		
		ArrayList<Notice> reply = new NoticeDao().selectReply(con, num);
		
		System.out.println("service 댓글: "+reply.size());
		close(con);
		
		return reply;
	}
	//검색용
	public ArrayList<Notice> searchValue(String searchValue) {
		Connection con = getConnection();
		ArrayList<Notice> list = new NoticeDao().searchValue(con, searchValue);
		System.out.println("service listsize:"+list.size());
		close(con);
		
		return list;
	}
	//제목으로 검색 결과 게시글 수
	public int getSearchTitleListCount(String title) {
		Connection con = getConnection();
		
		int listCount = new NoticeDao().getSearchTitleListCount(con, title);
		
		System.out.println("service 제목 listCount:"+listCount);
		close(con);
		
		return listCount;
	}
	//내용으로 검색 결과 게시글 수
	public int getSearchContentListCount(String content) {
		Connection con = getConnection();
		
		int listCount = new NoticeDao().getSearchContentListCount(con, content);
		
		System.out.println("service 내용 listCount:"+listCount);
		close(con);
		
		return listCount;
	}
	//제목으로 검색 결과 페이징
	public ArrayList<Notice> searchTitle(String title, int currentPage, int maxPage, int limit) {
		Connection con = getConnection();
		ArrayList<Notice> list = new NoticeDao().searchTitle(title, con, currentPage, maxPage, limit);
		
		System.out.println("title로 검색service: "+list.size());
		close(con);
		
		return list;
	}
	//글내용으로 검색 페이징
	public ArrayList<Notice> searchContent(String content, int currentPage, int maxPage, int limit) {
		Connection con = getConnection();
		ArrayList<Notice> list = new NoticeDao().searchContent(content, con, currentPage, maxPage, limit);
		
		System.out.println("내용으로 검색service: "+list.size());
		close(con);
		
		return list;
	}
	

}
